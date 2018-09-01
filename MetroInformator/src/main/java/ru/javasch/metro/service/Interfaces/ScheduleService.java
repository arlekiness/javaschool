package ru.javasch.metro.service.Interfaces;

import ru.javasch.metro.DTO.ScheduleDTO;
import ru.javasch.metro.model.Schedule;

import java.util.List;

public interface ScheduleService {
    public List<ScheduleDTO> getAllTrainsOnStation (String stationName);
}
