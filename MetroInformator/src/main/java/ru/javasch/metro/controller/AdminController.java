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
import ru.javasch.metro.service.interfaces.*;

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

    @Autowired
    private ControllerService controllerService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value="/dash")
    public ModelAndView enteringIntoAdmin() {
        Map<String, Object> pag = controllerService.pagination();
        List<Train> trains = (List<Train>)pag.get("trains");
        List<Station> stations = (List<Station>)pag.get("stations");
        trains = trains.subList(0, 20);
        stations = stations.subList(0, 19);
        Map<String, Object> modelMap = new HashMap<>();
        modelMap.put("trains", trains);
        modelMap.put("stations", stations);
        modelMap.put("trainPages", pag.get("trainPages"));
        modelMap.put("stationPages", pag.get("stationPages"));
        return new ModelAndView("dash", "model", modelMap);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/createTrain")
    public String creatingTrainForm() {
        return "createtrain";
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

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value="/dashtrain/{count}")
    public ModelAndView trainPagination(@PathVariable(value = "count") int pageNum) {
        Map<String, Object> pag = controllerService.pagination();
        List<Train> trains = (List<Train>)pag.get("trains");
        List<Station> stations = (List<Station>)pag.get("stations");
        if (pageNum != (int)pag.get("trainPages"))
            trains = trains.subList((pageNum - 1) * 20, (pageNum - 1) * 20 + 20);
        else
            trains = trains.subList((pageNum - 1) * 20, trains.size());
        stations = stations.subList(0, 19);

        Map<String, Object> modelMap = new HashMap<>();
        modelMap.put("trains", trains);
        modelMap.put("stations", stations);
        modelMap.put("trainPages", pag.get("trainPages"));
        modelMap.put("stationPages", pag.get("stationPages"));
        return new ModelAndView("dash", "model", modelMap);
    }

    @RequestMapping(value="/dashstation/{stcount}")
    public ModelAndView stationPagination(@PathVariable(value = "stcount") int stationNum) {
        Map<String, Object> pag = controllerService.pagination();
        List<Train> trains = (List<Train>)pag.get("trains");
        List<Station> stations = (List<Station>)pag.get("stations");
        switch (stationNum) {
            case 1:
                stations = stations.subList(0, 19);
                break;
            case 2:
                stations = stations.subList(19, 37);
                break;
            case 3:
                stations = stations.subList(37, 49);
                break;
            case 4:
                stations = stations.subList(49, 57);
                break;
            case 5:
                stations = stations.subList(57, 69);
                break;
            default:
                break;
        }
        trains = trains.subList(0, 20);
        Map<String, Object> modelMap = new HashMap<>();
        modelMap.put("trains", trains);
        modelMap.put("stations", stations);
        modelMap.put("trainPages", pag.get("trainPages"));
        modelMap.put("stationPages", pag.get("stationPages"));
        return new ModelAndView("dash", "model", modelMap);
    }
}
