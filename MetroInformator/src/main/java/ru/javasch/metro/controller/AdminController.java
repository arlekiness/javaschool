package ru.javasch.metro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.javasch.metro.exception.RuntimeBusinessLogicException;
import ru.javasch.metro.service.Implementations.PathFinderServiceImpl;
import ru.javasch.metro.service.Interfaces.PathFinderService;
import ru.javasch.metro.service.Interfaces.ScheduleService;
import ru.javasch.metro.service.Interfaces.StationService;
import ru.javasch.metro.service.Interfaces.TrainService;

import java.text.ParseException;
import java.util.List;

@Controller
public class AdminController {

    @Autowired
    TrainService trainService;

    @Autowired
    ScheduleService scheduleService;

    @Autowired
    StationService stationService;

    @Autowired
    PathFinderService pathService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value="/adminFunctions")
    public String enteringIntoAdmin() {
        return "adminka";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value="/createTrain")
    public ModelAndView creatingTrainForm() {
        return new ModelAndView("redirect:/createTrainTest");
    }

//    @PreAuthorize("hasRole('ROLE_ADMIN')")
//    @PostMapping("/createTrain")
//    public String creatingTrain(@RequestParam(value="trainName") String trainName,
//                                @RequestParam(value="stationBegin") String stationBegin,
//                                @RequestParam(value="date") String date) {
//        trainService.add("T981-P-18");
//    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/createTrainTest")
    public String creatingTrain() {
        try {
            scheduleService.addNewSchedules("T1-Prospekt Veteranov-Devyatkino-0645", "Devyatkino", "2018-04-09 06:45:00");
            scheduleService.addNewSchedules("T1-Devyatkino-Prospekt Veteranov-0645", "Prospekt Veteranov", "2018-04-09 06:45:00");
            return "adminka";
        } catch (RuntimeBusinessLogicException ex) {
            System.out.println(ex.getError());
            return "adminka";
        } catch (ParseException ex) {
            System.out.println("SmthWrong");return "adminka";}

    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/deleteTrain")
    public String deletingTrain() {
        try {
            trainService.delete("T1-Devyatkino-Prospekt Veteranov-0645");
            trainService.delete("T1-Prospekt Veteranov-Devyatkino-0645");
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

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/pathTest")
    public String pathTest() {
        return "selectStationsTest";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping ("/pathTest")
    public String pathTestTheBest(@RequestParam(value = "station1") String station1,
                           @RequestParam(value = "station2") String station2) {
//        pathService.pathFinder("Parnas", "Moskovskaya");
//        pathService.pathFinder("Komendantsky Prospekt", "Dostoyevskaya");
//        pathService.pathFinder("Volkovskaya", "Elektrosila");
//        pathService.pathFinder("Komendantsky Prospekt", "Vladimirskaya");
//        pathService.pathFinder("Sadovaya", "Devyatkino");
        pathService.pathFinder(station1, station2);
        return "adminka";
    }
}
