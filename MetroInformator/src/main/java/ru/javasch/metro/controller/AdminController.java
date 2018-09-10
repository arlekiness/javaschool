package ru.javasch.metro.controller;

import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.javasch.metro.exception.RuntimeBusinessLogicException;
import ru.javasch.metro.model.Station;
import ru.javasch.metro.model.Train;
import ru.javasch.metro.service.Implementations.PathFinderServiceImpl;
import ru.javasch.metro.service.Interfaces.PathFinderService;
import ru.javasch.metro.service.Interfaces.ScheduleService;
import ru.javasch.metro.service.Interfaces.StationService;
import ru.javasch.metro.service.Interfaces.TrainService;

import java.text.ParseException;
import java.util.List;

@Controller
@Log4j
public class AdminController {

    @Autowired
    TrainService trainService;

    @Autowired
    ScheduleService scheduleService;

    @Autowired
    StationService stationService;

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
        } catch (Exception ex) {
            System.out.println("Smth wrng");return "adminka";}
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/deleteTrain")
    public String deletingTrain() {
        try {
//            List<Train> =
            return "adminka";
        } catch (RuntimeBusinessLogicException ex) {
            System.out.println(ex.getError());
            return "adminka";
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/closeStation")
    public String closeStation() {
        try {
            stationService.closeStation("Dostoyevskaya");
            stationService.closeStation("Ploshchad Alexandra Nevskogo-2");
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
            stationService.openStation("Dostoyevskaya");
            stationService.openStation("Ploshchad Alexandra Nevskogo-2");
            return "adminka";
        } catch (RuntimeBusinessLogicException ex) {
            System.out.println(ex.getError());
            return "adminka";
        }
    }
}
