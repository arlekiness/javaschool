package ru.javasch.metro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.javasch.metro.DAO.Interfaces.TicketDAO;
import ru.javasch.metro.DTO.ScheduleDTO;
import ru.javasch.metro.model.Ticket;
import ru.javasch.metro.service.Interfaces.ScheduleService;
import ru.javasch.metro.service.Interfaces.TicketService;

import java.text.ParseException;
import java.util.List;

@Controller
public class ScheduleController {
    @Autowired
    ScheduleService scheduleService;

    @Autowired
    TicketService ticketService;

    @RequestMapping(value="/stationList")
    public ModelAndView stationSchedule(@RequestParam(value="stationSelect") String stationName) {
        List<ScheduleDTO> sch = scheduleService.getAllTrainsOnStation(stationName);
        try {
            List<Ticket> tick = ticketService.getTicketsByStationAndDate("Sennaya ploschad", "2018-07-20");
            List<Ticket> tick1 = ticketService.getTicketsByStation("Sennaya ploschad");
            System.out.println(tick.size());
            System.out.println(tick1.size());
        } catch (ParseException ex) {
            System.out.println("Obebos");
        }

        return new ModelAndView("station", "scheduleList", sch);
    }
}
