package ru.javasch.metro.service.Interfaces;

import ru.javasch.metro.DTO.ScheduleDTO;
import ru.javasch.metro.exception.BusinessLogicException;
import ru.javasch.metro.model.Schedule;
import ru.javasch.metro.model.Station;

import java.text.ParseException;
import java.util.List;

public interface ScheduleService {
    public void addSchedule (Schedule schedule);
    public List<ScheduleDTO> getAllTrainsOnStation (String stationName);
    public List<Station> addNewSchedules (String trainName, String stationName, String firstDate) throws ParseException;
}
