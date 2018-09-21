package ru.javasch.metro.service.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javasch.metro.dao.interfaces.ScheduleDAO;
import ru.javasch.metro.exception.ErrorCode;
import ru.javasch.metro.utils.Utils;
import ru.javasch.metro.exception.RuntimeBusinessLogicException;
import ru.javasch.metro.model.Schedule;
import ru.javasch.metro.model.Station;
import ru.javasch.metro.model.Train;
import ru.javasch.metro.service.interfaces.ScheduleService;
import ru.javasch.metro.service.interfaces.StationService;
import ru.javasch.metro.service.interfaces.TrainService;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    @Autowired
    private ScheduleDAO scheduleDAO;

    @Autowired
    private StationService stationService;

    @Autowired
    private TrainService trainService;

    @Override
    @Transactional
    public Schedule findByTrainAndStation (Station station, Train train, Date date) {return scheduleDAO.findByTrainAndStation(train, station, date);}

    /**add one schedule */
    @Override
    @Transactional
    public void addSchedule (Schedule schedule) {
        scheduleDAO.add(schedule);
    }

    /**
     View Schedule List on station*/
    @Override
    @Transactional
    public List<Schedule> getAllTrainsOnStation (String stationName, String dateString) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        Date date = format.parse(dateString);
        Station station = stationService.findByName(stationName);
        if (station.getStatus().getStatusName().equals("CLOSED"))
            throw new RuntimeBusinessLogicException(ErrorCode.STATION_CLOSED);

        List<Schedule> schedule = scheduleDAO.getByStationAndDate(station, date);
        return schedule;
    }
    /**
     Adding new Schedule List when ADMIN add new train*/
    @Override
    @Transactional
    public List<Station> addNewSchedules (String trainName, String stationName, String firstDate) throws ParseException {
        if (trainName == "" || stationName == "" || firstDate == "")
            throw new RuntimeBusinessLogicException(ErrorCode.EMPTY_FIELDS);
        Long Id = trainService.add(trainName);
        Train train = trainService.findById(Id);
        List<Station> stations = stationService.getAllStationOnBranch(stationName);
        if (!stationName.equals(stations.get(0).getName()))
            Collections.reverse(stations);
        Station endPointStation = stations.get(stations.size() - 1);
        Date date = Utils.parseToDateTime(firstDate);
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
            schedule.setTrain(train);
            schedule.setStation(st);
            schedule.setEndPointStation(endPointStation);
            addSchedule(schedule);
        }

        return stations;
    }

    @Override
    @Transactional
    public List<Schedule> getAllSchedulesByStationDateAndPath (Station stationBegin, Station stationEnd, Date date, Date now) {
        List<Station> stations = stationService.getAllStationOnBranch(stationBegin.getName());
        Station endPointStation;
        if (stationBegin.getNumberOnBranch() < stationEnd.getNumberOnBranch())
            endPointStation = stations.get(stations.size() - 1);
        else
            endPointStation = stations.get(0);
        return (List<Schedule>) scheduleDAO.getByStationsAndDate(stationBegin, endPointStation, date, now);
    }
}
