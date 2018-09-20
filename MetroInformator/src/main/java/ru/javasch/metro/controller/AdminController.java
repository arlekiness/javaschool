package ru.javasch.metro.controller;

import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.javasch.metro.exception.RuntimeBusinessLogicException;
import ru.javasch.metro.model.Ticket;
import ru.javasch.metro.service.interfaces.ScheduleService;
import ru.javasch.metro.service.interfaces.StationService;
import ru.javasch.metro.service.interfaces.TicketService;
import ru.javasch.metro.service.interfaces.TrainService;

import java.io.IOException;
import java.util.List;

@Controller
@Log4j
public class AdminController {

    @Autowired
    private TrainService trainService;

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private StationService stationService;

    @Autowired
    private TicketService ticketService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value="/adminFunctions")
    public String enteringIntoAdmin() {
        return "adminka";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/createTrain")
    public String creatingTrainForm() {
        return "createTrain";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/createTrain")
    public String creatingTrainForm(@RequestParam(value="trainName") String trainName,
                                    @RequestParam(value="stationName") String stationName,
                                    @RequestParam(value="datetime") String dateTime)
    {
        try {
            scheduleService.addNewSchedules(trainName, stationName, dateTime);
            return "adminka";
        } catch (RuntimeBusinessLogicException ex) {
            return "adminka";
        } catch (Exception ex) {return "adminka";}
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/deleteTrain")
    public String deletingTrain() {
        try {
            trainService.delete(92L);
            List<Ticket> tickets = ticketService.invalidateNonValidTickets();
            ticketService.sendInvalidateMessages(tickets);
            return "adminka";
        } catch (RuntimeBusinessLogicException ex) {
            System.out.println(ex.getError());
            return "adminka";
        } catch (Exception ex) {
            ex.printStackTrace();
            return "adminka";
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/closeStation")
    public String closeStation() {
        try {
            stationService.closeStation("Tekhnologichesky Institut-1");
            stationService.closeStation("Ploshchad Alexandra Nevskogo-1");
            stationService.closeStation("Nevsky Prospekt");
            stationService.closeStation("Parnas");
            return "adminka";
        } catch (RuntimeBusinessLogicException ex) {
            System.out.println(ex.getError());
            return "adminka";
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/openStation")
    public String openStation() {
        try {
            stationService.openStation("Tekhnologichesky Institut-1");
            stationService.openStation("Ploshchad Alexandra Nevskogo-1");
            stationService.openStation("Nevsky Prospekt");
            stationService.openStation("Parnas");
            return "adminka";
        } catch (RuntimeBusinessLogicException ex) {
            System.out.println(ex.getError());
            return "adminka";
        }
    }
}
