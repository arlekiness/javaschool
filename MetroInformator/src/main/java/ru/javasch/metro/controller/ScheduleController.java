package ru.javasch.metro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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

    @RequestMapping(value="/stationList")
    public ModelAndView stationSchedule(@RequestParam(value="stationSelect") String stationName) {
        List<ScheduleDTO> sch = scheduleService.getAllTrainsOnStation(stationName);
        ModelAndView model = new ModelAndView("station", "scheduleList", sch);
        model.addObject("scheduleList", sch);
        model.setViewName("station");
        model.addObject("login", userService.getUserId());
        return model;
    }
}
