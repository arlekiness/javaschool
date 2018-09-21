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
import ru.javasch.metro.model.User;
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
    private UserService userService;

    @Autowired
    private ControllerService controllerService;


    @GetMapping(value="/tickets")
    public String findtickets() {return "tickets"; }

    @RequestMapping(value="/ticketsFail")
    public ModelAndView ticketsFail() {return new ModelAndView("tickets", "noTickets", true); }


    @PostMapping("/tickets")
    public ModelAndView giveOptions(@RequestParam(value="begin") String beginStation,
                              @RequestParam(value="end") String endStation,
                              @RequestParam(value="date") String date,
                              HttpServletRequest req, HttpServletResponse resp) {
        try {
            List<List<Ticket>> tickets = controllerService.chainsOfTickets(beginStation, endStation, date);
            HttpSession session = req.getSession();
            session.setAttribute("TicketList", tickets);
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
        } catch (Exception ex) {
            return new ModelAndView("tickets", "systemError", true);
        }
    }

    @RequestMapping(value = "/registerTickets/{count}")
    public ModelAndView ticketRegistration(@PathVariable(value = "count") int count,
                                           HttpServletRequest req,
                                           HttpServletResponse resp) {
        try {
            HttpSession session = req.getSession();
            List<List<Ticket>> tickets = (List<List<Ticket>>) session.getAttribute("TicketList");
            List<Ticket> chain = tickets.get(count);
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String userName = authentication.getName();
            ticketService.registrateTicketsInSystem(chain, userName);
            session.removeAttribute("TicketList");
            return new ModelAndView("redirect:/myTickets");
        } catch (RuntimeBusinessLogicException ex) {
            HttpSession session = req.getSession();
            session.removeAttribute("TicketList");
            return new ModelAndView("redirect:/ticketsFail");
        } catch (Exception ex) {
            return new ModelAndView("redirect:/ticketsFail");
        }
    }

    @RequestMapping(value = "/myTickets")
    public ModelAndView myTickets(HttpServletRequest req,
                                  HttpServletResponse resp) {
        try {
            User user = userService.findAuthenticatedUser();
            List<Ticket> ticket = ticketService.findAllTicketsByUser();
            HttpSession session = req.getSession();
            session.setAttribute("loggedUser", user);
            session.setAttribute("myTicketList", ticket);
            return new ModelAndView("mytickets");
        } catch (Exception ex) {
            return new ModelAndView("mytickets");
        }
    }

}
