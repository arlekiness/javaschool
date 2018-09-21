package ru.javasch.metro.controller;

import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.javasch.metro.exception.RuntimeBusinessLogicException;
import ru.javasch.metro.model.Station;
import ru.javasch.metro.model.Ticket;
import ru.javasch.metro.model.Train;
import ru.javasch.metro.service.interfaces.ScheduleService;
import ru.javasch.metro.service.interfaces.StationService;
import ru.javasch.metro.service.interfaces.TicketService;
import ru.javasch.metro.service.interfaces.TrainService;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @RequestMapping(value="/dash")
    public ModelAndView enteringIntoAdmin() {
        Map<String, Object> modelMap = new HashMap<>();
        List<Train> trains = trainService.getAllTrains();
        List<Station> stations = stationService.getAllStations();
        System.out.println(trains.size());
        modelMap.put("trains", trains);
        modelMap.put("stations", stations);
        return new ModelAndView("dash", "model", modelMap);
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
    @GetMapping("/deleteTrain/{id}")
    public ModelAndView deletingTrain(@PathVariable(value = "id") Long id) {
        try {
            trainService.delete(id);
            List<Ticket> tickets = ticketService.invalidateNonValidTickets();
            ticketService.sendInvalidateMessages(tickets);
            return new ModelAndView("redirect:/dash");
        } catch (RuntimeBusinessLogicException ex) {
            return new ModelAndView("redirect:/dash");
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ModelAndView("redirect:/dash");
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/closeStation/{stationName}")
    public ModelAndView closeStation(@PathVariable(value = "stationName") String stationName) {
        try {
            stationService.closeStation(stationName);
            return new ModelAndView("redirect:/dash");
        } catch (RuntimeBusinessLogicException ex) {
            System.out.println(ex.getError());
            return new ModelAndView("redirect:/dash");
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/openStation/{stationName}")
    public ModelAndView openStation(@PathVariable(value = "stationName") String stationName) {
        try {
            stationService.openStation(stationName);
            return new ModelAndView("redirect:/dash");
        } catch (RuntimeBusinessLogicException ex) {
            System.out.println(ex.getError());
            return new ModelAndView("redirect:/dash");
        }
    }
}
