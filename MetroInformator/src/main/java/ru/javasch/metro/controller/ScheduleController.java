package ru.javasch.metro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.javasch.metro.exception.RuntimeBusinessLogicException;
import ru.javasch.metro.model.Schedule;
import ru.javasch.metro.service.Interfaces.ScheduleService;
import ru.javasch.metro.service.Interfaces.StationService;
import ru.javasch.metro.service.Interfaces.TicketService;
import ru.javasch.metro.service.Interfaces.UserService;

import java.text.ParseException;
import java.util.*;

@Controller
public class ScheduleController {
    @Autowired
    ScheduleService scheduleService;

    @Autowired
    TicketService ticketService;

    @Autowired
    StationService stationService;

    @Autowired
    UserService userService;

    @PostMapping("/stationList")
    public ModelAndView stationSchedule(@RequestParam(value="stationSelect") String stationName,
                                        @RequestParam(value="date") String date) {

        Map<String, Object> modelMap = new HashMap<>();
        try {
            List<Schedule> sch = scheduleService.getAllTrainsOnStation(stationName, date);
            modelMap.put("showSchedule", "true");
            modelMap.put("scheduleList", sch);
            return new ModelAndView("stationscheme", "model", modelMap);
        } catch (RuntimeBusinessLogicException ex) {
            modelMap.put("closedStationStatus", "true");
            return new ModelAndView("stationscheme", "model", modelMap);
        } catch (ParseException ex) {
            modelMap.put("closedStationStatus", "true");
            return new ModelAndView("stationscheme", "model", modelMap);
        }
    }
}
