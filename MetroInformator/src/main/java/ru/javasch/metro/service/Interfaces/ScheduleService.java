package ru.javasch.metro.service.Interfaces;

import ru.javasch.metro.model.Schedule;

import java.util.List;

public interface ScheduleService {
    public List<Schedule> getAllTrainsOnStation (String stationName);
}
