package ru.javasch.metro.service.implementations;

import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javasch.metro.dao.interfaces.ScheduleDAO;
import ru.javasch.metro.exception.ErrorCode;
import ru.javasch.metro.exception.RuntimeBusinessLogicException;
import ru.javasch.metro.model.Schedule;
import ru.javasch.metro.model.Station;
import ru.javasch.metro.model.Train;
import ru.javasch.metro.service.interfaces.ScheduleService;
import ru.javasch.metro.service.interfaces.StationService;
import ru.javasch.metro.service.interfaces.TrainService;
import ru.javasch.metro.utils.Utils;

import org.springframework.transaction.annotation.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Log4j
public class ScheduleServiceImpl implements ScheduleService {

    @Autowired
    private ScheduleDAO scheduleDAO;

    @Autowired
    private StationService stationService;

    @Autowired
    private TrainService trainService;

    @Override
    public Schedule findByTrainAndStation(Station station, Train train, Date date) {
        return scheduleDAO.findByTrainAndStation(train, station, date);
    }

    /**
     * add one schedule
     */
    @Override
    @Transactional
    public void addSchedule(Schedule schedule) {
        scheduleDAO.add(schedule);
    }

    /**
     * View Schedule List on station
     */
    @Override
    @Transactional(readOnly = true)
    public List<Schedule> getAllTrainsOnStation(String stationName, String dateString) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        Date date = format.parse(dateString);
        Date now = new Date();
        if (date.before(now))
            throw new RuntimeBusinessLogicException(ErrorCode.INCORRECT_DATE_SCHEDULE);
        Station station = stationService.findByName(stationName);
        if (station.getStatus().getStatusName().equals("CLOSED"))
            throw new RuntimeBusinessLogicException(ErrorCode.STATION_CLOSED);

        List<Schedule> schedule = scheduleDAO.getByStationAndDate(station, date);
        return schedule;
    }

    /**
     * Adding new Schedule List when ADMIN add new train
     */
    @Override
    @Transactional
    public List<Station> addNewSchedules(String trainName, String stationName, String firstDate, String firstTime) throws ParseException {
        if (trainName == "" || stationName == "" || firstDate == "" || firstTime == "") {
            log.info("EXCEPTION: " + ErrorCode.EMPTY_FIELDS_TRAIN_FORM);
            throw new RuntimeBusinessLogicException(ErrorCode.EMPTY_FIELDS_TRAIN_FORM);
        }
        if (!(stationName.equals("Devyatkino") || stationName.equals("Devyatkino") ||
                stationName.equals("Parnas") || stationName.equals("Prospekt Veteranov") ||
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
        System.out.println(train.getTrainName());
        List<Station> stations = stationService.getAllStationOnBranch(stationName);
        if (!stationName.equals(stations.get(0).getName()))
            Collections.reverse(stations);
        Station endPointStation = stations.get(stations.size() - 1);
        Date now = new Date();
        Date date = Utils.parseToDateTime(firstDate, firstTime);
        if (date.before(now)) {
            log.info("EXCEPTION: " + ErrorCode.TRAIN_IN_PAST);
            throw new RuntimeBusinessLogicException(ErrorCode.TRAIN_IN_PAST);
        }
        Set<Schedule> scheduleSet = new HashSet<>();
        train.setSchedule(scheduleSet);
        Calendar cal = Calendar.getInstance();
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
        }

        return stations;
    }

    @Override
    @Transactional
    public List<Schedule> getAllSchedulesByStationDateAndPath(Station stationBegin, Station stationEnd, Date date, Date now) {
        if (date.before(now)) {
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

    @Override
    public List<Schedule> getPastSchedules() {return scheduleDAO.getPastSchedules();}

    @Override
    @Transactional
    public void deletePastSchedules(Schedule sch) {scheduleDAO.delete(sch);}
}
