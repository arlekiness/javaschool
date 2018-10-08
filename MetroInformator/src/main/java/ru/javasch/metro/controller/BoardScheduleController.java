package ru.javasch.metro.controller;

import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.javasch.metro.dto.ScheduleDTO;
import ru.javasch.metro.dto.StationDTO;
import ru.javasch.metro.service.interfaces.ScheduleService;
import ru.javasch.metro.service.interfaces.StationService;

import java.util.List;

@RestController
@Log4j
public class BoardScheduleController {
    @Autowired
    ScheduleService scheduleService;

    @Autowired
    StationService stationService;

    @GetMapping("/boardschedule/schedulestoday")
    public ResponseEntity<?> getSchedulesForToday() {
        log.info("BOARD INITIALIZER STARTED WORK");
        List<ScheduleDTO> schedules = scheduleService.getAllForToday();
        log.info("RETRIEVED " + schedules.size() + " RECORDS");
        return ResponseEntity.ok(schedules);
    }

    @GetMapping("/boardschedule/allstations")
    public ResponseEntity<?> getStationList() {
        log.info("STATION BOARD INITIALIZER STARTED WORK");
        List<StationDTO> stations = stationService.getAll();
        log.info("RETRIEVED " + stations.size() + " RECORDS");
        return ResponseEntity.ok(stations);
    }
}
