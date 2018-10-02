package ru.javasch.metro.controller;

import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.javasch.metro.dto.ScheduleDTO;
import ru.javasch.metro.service.interfaces.ScheduleService;

import java.util.List;

@RestController
@Log4j
public class BoardScheduleController {
    @Autowired
    ScheduleService scheduleService;

    @GetMapping("/boardschedule/schedulestoday")
    public ResponseEntity<?> getSchedulesForToday() {
        List<ScheduleDTO> schedules = scheduleService.getAllForToday();
        return ResponseEntity.ok(schedules);
    }
}
