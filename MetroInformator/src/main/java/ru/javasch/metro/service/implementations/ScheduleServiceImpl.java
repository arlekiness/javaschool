package ru.javasch.metro.service.implementations;

import lombok.extern.log4j.Log4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javasch.metro.dao.interfaces.ScheduleDAO;
import ru.javasch.metro.dto.ScheduleDTO;
import ru.javasch.metro.exception.ErrorCode;
import ru.javasch.metro.exception.RuntimeBusinessLogicException;
import ru.javasch.metro.model.LastDateSchedule;
import ru.javasch.metro.model.Schedule;
import ru.javasch.metro.model.Station;
import ru.javasch.metro.model.Train;
import ru.javasch.metro.service.interfaces.ScheduleService;
import ru.javasch.metro.service.interfaces.StationService;
import ru.javasch.metro.service.interfaces.TrainService;
import ru.javasch.metro.utils.Utils;

import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;

@Service
@Log4j
public class ScheduleServiceImpl implements ScheduleService {

    @Autowired
    private ScheduleDAO scheduleDAO;

    @Autowired
    private StationService stationService;

    @Autowired
    private TrainService trainService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private LastDateService lastDateService;

    @Autowired
    private MessageQueueService messageQueueService;

    @Override
    public Schedule findByTrainAndStation(Station station, Train train, Date date) {
        return scheduleDAO.findByTrainAndStation(train, station, date);
    }

    /**
     * ADD ONE SCHEDULE RECORD
     */
    @Override
    public void addSchedule(Schedule schedule) {
        scheduleDAO.add(schedule);
    }

    /**
     * VIEW SCHEDULE LIST ON STATION
     */
    @Override
    @Transactional
    public List<Schedule> getAllTrainsOnStation(String stationName, String dateString) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        Date date = format.parse(dateString);
        Date now = new Date();
        if (Utils.checkDatesOnCorrectness(date, now))
            throw new RuntimeBusinessLogicException(ErrorCode.INCORRECT_DATE_SCHEDULE);
        Station station = stationService.findByName(stationName);
        if (station.getStatus().getStatusName().equals("CLOSED"))
            throw new RuntimeBusinessLogicException(ErrorCode.STATION_CLOSED);
        List<Schedule> schedule = scheduleDAO.getByStationAndDate(station, date);
        return schedule;
    }

    /**
     * AUTOMATIC ADDING NEW SCHEDULE LIST WHEN ADMIN ADD NEW TRAIN
     */
    @Override
    @Transactional
    public List<Station> addNewSchedules(String trainName, String stationName, String firstDate, String firstTime) throws ParseException, IOException, TimeoutException {
        if (trainName == "" || stationName == "" || firstDate == "" || firstTime == "") {
            log.info("EXCEPTION: " + ErrorCode.EMPTY_FIELDS_TRAIN_FORM);
            throw new RuntimeBusinessLogicException(ErrorCode.EMPTY_FIELDS_TRAIN_FORM);
        }
        if (!(stationName.equals("Devyatkino") || stationName.equals("Prospekt Veteranov") ||
                stationName.equals("Parnas") || stationName.equals("Kupchino") ||
                stationName.equals("Begovaya") || stationName.equals("Rybatskoye") ||
                stationName.equals("Spasskaya") || stationName.equals("Ulitsa Dybenko") ||
                stationName.equals("Komendantsky Prospekt") || stationName.equals("Mezhdunarodnaya"))){
            log.info("EXCEPTION: " + ErrorCode.DONT_KNOW_STATION);
            throw new RuntimeBusinessLogicException(ErrorCode.DONT_KNOW_STATION);
        }
        Integer hour = Integer.parseInt(new StringBuilder(firstTime).delete(2, 5).toString());
        Integer minutes = Integer.parseInt(new StringBuilder(firstTime).delete(0, 3).toString());
        if (hour == 13 && minutes > 15 || hour > 13) {
            log.info("EXCEPTION: " + ErrorCode.TO_LATE_FOR_TRAIN);
            throw new RuntimeBusinessLogicException(ErrorCode.TO_LATE_FOR_TRAIN);
        }
        Long Id = trainService.add(trainName);
        Train train = trainService.findById(Id);
        List<Station> stations = stationService.getAllStationOnBranch(stationName);
        if (!stationName.equals(stations.get(0).getName()))
            Collections.reverse(stations);
        Station endPointStation = stations.get(stations.size() - 1);
        Date now = new Date();
        Date date = Utils.parseToDateTime(firstDate, firstTime);
        LastDateSchedule lastDateSchedule = lastDateService.getLastDate();
        Date lastDate = lastDateSchedule.getDateSchedule();
        if (date.after(lastDate))
            throw new RuntimeBusinessLogicException(ErrorCode.NOT_AUTHORIZED_ADDING);
        Calendar calLastDate = Calendar.getInstance();
        calLastDate.setTime(lastDate);
        Calendar calScheduleDate = Calendar.getInstance();
        calScheduleDate.setTime(date);
        calScheduleDate.set(Calendar.YEAR, calLastDate.get(Calendar.YEAR));
        calScheduleDate.set(Calendar.MONTH, calLastDate.get(Calendar.MONTH));
        calScheduleDate.set(Calendar.DAY_OF_MONTH, calLastDate.get(Calendar.DAY_OF_MONTH));
        Date checked = calScheduleDate.getTime();
        System.out.println(checked);
        List<Schedule> schedules = scheduleDAO.getForCheckOnCreatingTrain(lastDate, stations.get(0), endPointStation);
        for (Schedule sch : schedules) {
            Date departure = sch.getDateDeparture();
            Date arrival = sch.getDateArrival();
            Calendar arrivalDate = Calendar.getInstance();
            arrivalDate.setTime(arrival);
            arrivalDate.add(Calendar.MINUTE, -5);
            arrival = arrivalDate.getTime();
            if ((checked.after(arrival) || checked.equals(arrival)) && (checked.before(departure) || checked.equals(departure)))
                throw new RuntimeBusinessLogicException(ErrorCode.THAT_TIME_ALREADY_USED_BY_ANOTHER_TRAIN);
        }
        if (date.before(now)) {
            log.info("EXCEPTION: " + ErrorCode.TRAIN_IN_PAST);
            throw new RuntimeBusinessLogicException(ErrorCode.TRAIN_IN_PAST);
        }
        Calendar cal = Calendar.getInstance();
        List<Schedule> schedulesForUpdating = new ArrayList<>();
        for (Station st : stations) {
            Schedule schedule = new Schedule();
            schedule.setDateArrival(date);
            cal.setTime(date);
            cal.add(Calendar.MINUTE, 5);
            date = cal.getTime();
            schedule.setDateDeparture(date);
            cal.setTime(date);
            cal.add(Calendar.MINUTE, 30);
            date = cal.getTime();
            schedule.setStation(st);
            schedule.setEndPointStation(endPointStation);
            schedule.setTrain(train);
            addSchedule(schedule);
            schedulesForUpdating.add(schedule);
        }
        updateSchedules(schedulesForUpdating);
        messageQueueService.produceMsg("created new schedules");
        return stations;
    }

    /**METHOD FOR AUTOMATIC UPDATING SCHEDULES
     *
     * @param schedules
     */
    @Override
    public void updateSchedules(List<Schedule> schedules) throws IOException, TimeoutException {
        log.info("UPDATER BEGIN WORK");
        Date now = new Date();
        LastDateSchedule last = lastDateService.getLastDate();
        int dateCounter = 1;
        Date lastDateSchedule = last.getDateSchedule();
        Date from = schedules.get(0).getDateDeparture();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(from);
        Utils.setHMSMfieldsInZero(calendar);
        calendar.add(Calendar.HOUR, 24);
        Date fromDateSchedule = calendar.getTime();
        while (fromDateSchedule.before(lastDateSchedule)) {
            System.out.println(fromDateSchedule);
            for (Schedule sch : schedules) {
                Schedule schedule = new Schedule();

                Date dateDeparture = sch.getDateDeparture();
                Date dateArrival = sch.getDateArrival();
                Calendar calDep = Calendar.getInstance();
                calDep.setTime(dateDeparture);
                Calendar calArr = Calendar.getInstance();
                calArr.setTime(dateArrival);
                calArr.add(Calendar.HOUR, 24 * dateCounter);
                calDep.add(Calendar.HOUR, 24 * dateCounter);
                Date newDateArrival = calArr.getTime();
                Date newDateDeparture = calDep.getTime();
                schedule.setDateDeparture(newDateDeparture);
                schedule.setDateArrival(newDateArrival);



                schedule.setTrain(sch.getTrain());
                schedule.setEndPointStation(sch.getEndPointStation());
                schedule.setStation(sch.getStation());

                addSchedule(schedule);

            }
            dateCounter++;
            Calendar forFromDateSchedule = Calendar.getInstance();
            forFromDateSchedule.setTime(fromDateSchedule);
            forFromDateSchedule.add(Calendar.HOUR, 24);
            fromDateSchedule = forFromDateSchedule.getTime();
        }
        messageQueueService.produceMsg("updated schedules");
        log.info("UPDATER ENDED WORK");
    }



    /**METHOD RETURNING ALL SCHEDULES RECORDS THROUGH STATION ON DATE
     * AND CHECKING DATE (YOU CAN'T SEE RECORDS IN PAST)
     * @param stationBegin
     * @param stationEnd
     * @param date
     * @param now
     * @return
     */
    @Override
    @Transactional
    public List<Schedule> getAllSchedulesByStationDateAndPath(Station stationBegin, Station stationEnd, Date date, Date now) {
        if (Utils.checkDatesOnCorrectness(date, now) == true) {
            log.info("EXCEPTION: " + ErrorCode.INCORRECT_DATE_TICKETS);
            throw new RuntimeBusinessLogicException(ErrorCode.INCORRECT_DATE_TICKETS);
        }
        List<Station> stations = stationService.getAllStationOnBranch(stationBegin.getName());
        Station endPointStation;
        if (stationBegin.getNumberOnBranch() < stationEnd.getNumberOnBranch())
            endPointStation = stations.get(stations.size() - 1);
        else
            endPointStation = stations.get(0);
        return (List<Schedule>) scheduleDAO.getByStationsAndDate(stationBegin, endPointStation, date, now);
    }

    /**HELPER METHOD FOR
     * @see ru.javasch.metro.scheduled.ScheduledTasks#deletePastSchedules()
     * @return
     */
    @Override
    public List<Schedule> getPastSchedules() {return scheduleDAO.getPastSchedules();}

    @Override
    @Transactional
    public void deletePastSchedules(Schedule sch) {scheduleDAO.delete(sch);}

    @Override
    public  List<Schedule> getForDate (Date date) {
        return (List<Schedule>) scheduleDAO.getForDate(date);
    }

    @Override
    public  List<Schedule> getByTrain (Train train) {return scheduleDAO.findByTrain(train);}

    /**DTO METHODS FOR BOARD*/
    @Override
    @Transactional
    public List<ScheduleDTO> getAll() {
        List<Schedule> schedules = scheduleDAO.getAll();
        return schedules.stream()
                .map(x -> modelMapper.map(x, ScheduleDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<ScheduleDTO> getAllForToday() {
        List<Schedule> schedules = scheduleDAO.getForToday();
        return schedules.stream()
                .map(x -> modelMapper.map(x, ScheduleDTO.class))
                .collect(Collectors.toList());
    }


}
