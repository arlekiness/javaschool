package ru.javasch.metro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.javasch.metro.DAO.Interfaces.StationDAO;
import ru.javasch.metro.DAO.Interfaces.TicketDAO;
import ru.javasch.metro.DTO.ScheduleDTO;
import ru.javasch.metro.model.Station;
import ru.javasch.metro.model.Ticket;
import ru.javasch.metro.model.User;
import ru.javasch.metro.service.Implementations.SecureService;
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
    public ModelAndView stationSchedule(@RequestParam(value="stationSelect") String stationName) {
        System.out.println("Here");
        List<ScheduleDTO> sch = scheduleService.getAllTrainsOnStation(stationName);
        System.out.println(sch.size());
        Map<String, Object> modelMap = new HashMap<>();
        modelMap.put("showSchedule", "true");
        modelMap.put("scheduleList", sch);
        return new ModelAndView("stationscheme", "model", modelMap);
    }
}
