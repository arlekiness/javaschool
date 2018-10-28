package ru.javasch.metro.controller;

import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.javasch.metro.model.Ticket;
import ru.javasch.metro.model.User;
import ru.javasch.metro.service.interfaces.ControllerService;
import ru.javasch.metro.service.interfaces.TicketService;
import ru.javasch.metro.service.interfaces.UserService;
import ru.javasch.metro.utils.URLs;
import ru.javasch.metro.utils.VIEWs;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.util.List;

/**
 * ************************************************
 * CONTROLLER FOR BUYING TICKET OPERATIONS
 * ************************************************
 */

@Controller
@Log4j
public class BuyingTicketController {

    @Autowired
    private TicketService ticketService;

    @Autowired
    private UserService userService;

    @Autowired
    private ControllerService controllerService;

    @GetMapping(value = URLs.TICKETS)
    public String findtickets() {
        return VIEWs.TICKETS;
    }

    @RequestMapping(value = URLs.TICKETS_FAIL)
    public ModelAndView ticketsFail() {
        return new ModelAndView(VIEWs.TICKETS, "noTickets", true);
    }


    /**FORM TICKET CHAINS FOR USER BY TWO STATION AND DATE
     *
     * @param beginStation begin station
     * @param endStation end station
     * @param date date of trip
     * @param req request
     * @param resp response
     * @return
     */
    @PostMapping(URLs.TICKETS)
    public ModelAndView giveOptions(@RequestParam(value = "begin") String beginStation,
                                    @RequestParam(value = "end") String endStation,
                                    @RequestParam(value = "date") String date,
                                    HttpServletRequest req, HttpServletResponse resp) {
        try {
            List<List<Ticket>> tickets = controllerService.chainsOfTickets(beginStation, endStation, date);
            HttpSession session = req.getSession();
            session.setAttribute("TicketList", tickets);
            return new ModelAndView(VIEWs.TICKETTABLE);
        } catch (ParseException ex) {
            log.error("SYSTEM EXCEPTION", ex);
            return new ModelAndView(VIEWs.TICKETS, "systemError", true);
        }
    }

    /**REGISTERING TICKETS IN SYSTEM
     *
     * @param count count of tickets
     * @param req request
     * @param resp response
     * @return
     */

    @RequestMapping(value = URLs.TICKET_BOOKING)
    public ModelAndView ticketRegistration(@PathVariable(value = "count") int count,
                                           HttpServletRequest req,
                                           HttpServletResponse resp) {
        HttpSession session = req.getSession();
        List<List<Ticket>> tickets = (List<List<Ticket>>) session.getAttribute("TicketList");
        List<Ticket> chain = tickets.get(count);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        ticketService.registrateTicketsInSystem(chain, userName);
        session.removeAttribute("TicketList");
        return new ModelAndView(URLs.REDIRECT_MY_TICKETS);
    }

    /** USER TICKETS TABLE
     *
     * @param req request
     * @param resp response
     * @return
     */
    @RequestMapping(value = URLs.MY_TICKETS)
    public ModelAndView myTickets(HttpServletRequest req,
                                  HttpServletResponse resp) {
        User user = userService.findAuthenticatedUser();
        List<Ticket> ticket = ticketService.findAllTicketsByUser();
        HttpSession session = req.getSession();
        session.setAttribute("loggedUser", user);
        session.setAttribute("myTicketList", ticket);
        return new ModelAndView(VIEWs.MY_TICKETS);
    }

}
