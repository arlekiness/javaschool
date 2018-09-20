package ru.javasch.metro.controller;

import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.javasch.metro.exception.ErrorCode;
import ru.javasch.metro.exception.RuntimeBusinessLogicException;
import ru.javasch.metro.model.Schedule;
import ru.javasch.metro.model.Station;
import ru.javasch.metro.model.Ticket;
import ru.javasch.metro.service.interfaces.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
    private UserService userService;

    @Autowired
    private PathFinderService pathFinderService;


    @GetMapping(value="/tickets")
    public String findtickets() {
        return "tickets";
    }

    @GetMapping("/giveOptions")
    public String giveOptionsGet() {
        return "findtickets";
    }

    @PostMapping("/tickets")
    public ModelAndView giveOptions(@RequestParam(value="begin") String beginStation,
                              @RequestParam(value="end") String endStation,
                              @RequestParam(value="date") String date,
                              HttpServletRequest req, HttpServletResponse resp) {
        try {
            List<Station> stations = pathFinderService.pathFinder(beginStation, endStation);
            List<List<Station>> segments = stationService.formSegments(stations);
            stationService.checkSegments(segments);
            List<List<Station>> pathSegments = stationService.findPathSegments(segments);
            String path = ticketService.formMessageAboutPath(segments);
//        List<Ticket> tickets =
            List<Schedule> schedules = ticketService.formFirstTicket(pathSegments, date);
            if (schedules.size() == 0)
                throw new RuntimeBusinessLogicException(ErrorCode.NO_TRAIN_ON_DATE);
            List<List<Ticket>> tickets = ticketService.formTicketChains(pathSegments, schedules);
            HttpSession session = req.getSession();
            session.setAttribute("TicketList", tickets);
            session.setAttribute("chainId", 1);
            return new ModelAndView("tickettable");
        } catch (RuntimeBusinessLogicException ex) {
            if (ex.getError() == ErrorCode.BEGIN_STATION_CLOSED)
                return new ModelAndView("tickets", "beginStationClosed", true);
            else if (ex.getError() == ErrorCode.END_STATION_CLOSED)
                return new ModelAndView("tickets", "endStationClosed", true);
            else if (ex.getError() == ErrorCode.ATS_ARE_CLOSED)
                return new ModelAndView("tickets", "ATSClosed", true);
            else
                return new ModelAndView("tickets", "noTrainsOnDate", true);

        } catch (ParseException ex) {
            System.out.println("Huinya kakaya-to");
        } catch (Exception ex) {
            ex.printStackTrace();
        }



        return new ModelAndView("findtickets");
    }

    @RequestMapping(value = "/requestTicket/{count}")
    public ModelAndView ticketRegistration(@PathVariable(value = "count") int count,
                                           HttpServletRequest req,
                                           HttpServletResponse resp) {
        try {
            HttpSession session = req.getSession();
            List<List<Ticket>> tickets = (List<List<Ticket>>) session.getAttribute("TicketList");
            List<Ticket> chain = tickets.get(count - 1);
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String userName = authentication.getName();
            ticketService.registrateTicketsInSystem(chain, userName);
            session.removeAttribute("TicketList");
            return new ModelAndView("redirect:/giveOptions");
        } catch (RuntimeBusinessLogicException ex) {
            HttpSession session = req.getSession();
            session.removeAttribute("TicketList");
            return new ModelAndView("redirect:/giveOptions");
        }
    }


    @RequestMapping(value="/tickettabletest")
    public String testTable() {
        return "tickettable";
    }


}
