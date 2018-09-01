package ru.javasch.metro.service.Implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javasch.metro.DAO.Interfaces.ScheduleDAO;
import ru.javasch.metro.DAO.Interfaces.StationDAO;
import ru.javasch.metro.DTO.ScheduleDTO;
import ru.javasch.metro.model.Schedule;
import ru.javasch.metro.model.Station;
import ru.javasch.metro.service.Interfaces.ScheduleService;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService {
    @Autowired
    private ScheduleDAO scheduleDAO;

    @Autowired
    private StationDAO stationDAO;

    @Override
    @Transactional
    public List<ScheduleDTO> getAllTrainsOnStation (String stationName) {
        Station station = stationDAO.findByName(stationName);
        List<Schedule> schedule = scheduleDAO.getByStation(station);
        System.out.println(schedule.size());
        List<ScheduleDTO> scheduleList = new ArrayList<>();
        for (Schedule sch : schedule) {
            ScheduleDTO schDTO = new ScheduleDTO();
            schDTO.setDateArrival(sch.getDateArrival().toString());
            schDTO.setDateDeparture(sch.getDateDeparture().toString());
            schDTO.setStation(sch.getStation().getName());
            schDTO.setEndPointStationName(sch.getEndPointStation().getName());
            schDTO.setTrainName(sch.getTrain().getTrainName());
            scheduleList.add(schDTO);
        }
        System.out.println(scheduleList.size());
        return scheduleList;
    }
}
