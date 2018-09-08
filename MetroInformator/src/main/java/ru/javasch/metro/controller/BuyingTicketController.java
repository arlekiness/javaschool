package ru.javasch.metro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.javasch.metro.DAO.Interfaces.StationDAO;
import ru.javasch.metro.DTO.TicketDTO;
import ru.javasch.metro.model.Schedule;
import ru.javasch.metro.model.Station;
import ru.javasch.metro.model.Ticket;
import ru.javasch.metro.model.Train;
import ru.javasch.metro.service.Interfaces.PathFinderService;
import ru.javasch.metro.service.Interfaces.StationService;
import ru.javasch.metro.service.Interfaces.TicketService;
import ru.javasch.metro.service.Interfaces.TrainService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class BuyingTicketController {

    @Autowired
    private TicketService ticketService;

    @Autowired
    private StationService stationService;

    @Autowired
    private TrainService trainService;

    @Autowired
    private PathFinderService pathFinderService;


    @RequestMapping(value="/findTickets")
    public String findtickets() {
        return "findtickets";
    }

    @PostMapping("/giveOptions")
    public String giveOptions(@RequestParam(value="begin") String beginStation,
                              @RequestParam(value="end") String endStation,
                              @RequestParam(value="date") String date) {
        try {
            List<Station> stations = pathFinderService.pathFinder(beginStation, endStation);
            List<List<Station>> segments = stationService.formSegments(stations);
            stationService.checkSegments(segments);
            List<List<Station>> pathSegments = stationService.findPathSegments(segments);
            String path = ticketService.formMessageAboutPath(segments);
            System.out.println(path);
//        List<Ticket> tickets =
            List<Schedule> schedules = ticketService.formFirstTicket(pathSegments, date);
            List<List<Ticket>> tickets = ticketService.formTicketChains(pathSegments, schedules);
        } catch (ParseException ex) {
            System.out.println("Huinya kakaya-to");
        }



        return "findtickets";
    }



}
