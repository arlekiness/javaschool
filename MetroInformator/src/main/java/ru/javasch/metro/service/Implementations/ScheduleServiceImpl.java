package ru.javasch.metro.service.Implementations;

import org.springframework.beans.factory.annotation.Autowired;
import ru.javasch.metro.DAO.Interfaces.ScheduleDAO;
import ru.javasch.metro.DAO.Interfaces.StationDAO;
import ru.javasch.metro.model.Schedule;
import ru.javasch.metro.model.Station;
import ru.javasch.metro.service.Interfaces.ScheduleService;

import javax.transaction.Transactional;
import java.util.List;

public class ScheduleServiceImpl implements ScheduleService {
    @Autowired
    private ScheduleDAO scheduleDAO;

    @Autowired
    private StationDAO stationDAO;

    @Override
    @Transactional
    public List<Schedule> getAllTrainsOnStation (String stationName) {
        Station station = stationDAO.findByName(stationName);
        return scheduleDAO.getByStation(station);
    }
}
