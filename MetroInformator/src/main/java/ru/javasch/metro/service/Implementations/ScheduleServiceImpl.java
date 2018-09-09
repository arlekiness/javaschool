package ru.javasch.metro.service.Implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javasch.metro.DAO.Interfaces.ScheduleDAO;
import ru.javasch.metro.configuration.constants.Utils;
import ru.javasch.metro.exception.RuntimeBusinessLogicException;
import ru.javasch.metro.model.Schedule;
import ru.javasch.metro.model.Station;
import ru.javasch.metro.model.Train;
import ru.javasch.metro.service.Interfaces.ScheduleService;
import ru.javasch.metro.service.Interfaces.StationService;
import ru.javasch.metro.service.Interfaces.TrainService;

import javax.transaction.Transactional;
import java.text.ParseException;
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
    public Schedule findByTrainAndStation (Station station, Train train) {return scheduleDAO.findByTrainAndStation(train, station);}

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
        Date date = Utils.parseToDate(dateString);
        Station station = stationService.findByName(stationName);
        if (station.getStatus().getStatusName().equals("CLOSED"))
            throw new RuntimeBusinessLogicException("Station closed for some reason");

        List<Schedule> schedule = scheduleDAO.getByStationAndDate(station, date);
        return schedule;
    }
    /**
     Adding new Schedule List when ADMIN add new train*/
    @Override
    @Transactional
    public List<Station> addNewSchedules (String trainName, String stationName, String firstDate) throws ParseException {
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
