package ru.javasch.metro.controller;

import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.javasch.metro.model.Schedule;
import ru.javasch.metro.model.Station;
import ru.javasch.metro.model.Ticket;
import ru.javasch.metro.service.Interfaces.PathFinderService;
import ru.javasch.metro.service.Interfaces.StationService;
import ru.javasch.metro.service.Interfaces.TicketService;
import ru.javasch.metro.service.Interfaces.TrainService;

import java.text.ParseException;
import java.util.List;

@Controller
@Log4j
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
            System.out.println(tickets.size());

            for (List<Ticket> tick : tickets) {
                for (Ticket t : tick)
                    System.out.print(t.getBranch().getColor() + "****" + t.getStationBegin().getName() + "----->" + t.getStationEnd().getName() + "Date: " + t.getTicketDateDeparture() + " - " + t.getTicketDateArrival() + "//====//");
                System.out.println("********");
            }
        } catch (ParseException ex) {
            System.out.println("Huinya kakaya-to");
        }



        return "findtickets";
    }



}
