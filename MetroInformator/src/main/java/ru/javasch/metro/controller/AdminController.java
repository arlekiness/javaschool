package ru.javasch.metro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import lombok.extern.log4j.Log4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.javasch.metro.exception.RuntimeBusinessLogicException;
import ru.javasch.metro.model.Station;
import ru.javasch.metro.model.Ticket;
import ru.javasch.metro.model.Train;
import ru.javasch.metro.service.interfaces.*;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@Log4j
public class AdminController {

    private static final int TRAIN_NUM_ON_PAGE = 20;

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

    /** Block for
                create-delete trains*/

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/createtrain")
    public String creatingTrainForm() {
        return "createtrain";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/createtrain")
    public ModelAndView creatingTrainForm(@RequestParam(value="trainname") String trainName,
                                          @RequestParam(value="startstation") String stationName,
                                          @RequestParam(value="date") String date,
                                          @RequestParam(value="time") String time)
    {
        try {
            scheduleService.addNewSchedules(trainName, stationName, date, time);
            return new ModelAndView("redirect:/dashtrain", "success", true);
        } catch (ParseException ex) {
            log.error("SYSTEM EXCEPTION", ex);
            return new ModelAndView("redirect:/dashtrain", "systemError", true);
        }
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/deleteTrain/{id}")
    public ModelAndView deletingTrain(@PathVariable(value = "id") Long id) {
        try {
            trainService.delete(id);
            List<Ticket> tickets = ticketService.invalidateNonValidTickets();
            return new ModelAndView("redirect:/dashtrain", "deleted", true);
        } catch (Exception ex) {
            log.error("SYSTEM EXCEPTION", ex);
            return new ModelAndView("redirect:/dashtrain", "systemError", true);
        }
    }

    /** Block for
                open-close stations*/

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/closeStation/{stationName}")
    public ModelAndView closeStation(@PathVariable(value = "stationName") String stationName) {
            stationService.closeStation(stationName);
            String color = stationService.findByName(stationName).getBranch().getColor();
            return new ModelAndView(controllerService.stationSwitchHelper(color));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/openStation/{stationName}")
    public ModelAndView openStation(@PathVariable(value = "stationName") String stationName) {
            stationService.openStation(stationName);
            String color = stationService.findByName(stationName).getBranch().getColor();
            return new ModelAndView(controllerService.stationSwitchHelper(color));
    }

    /** Block for
                admin panel
                    train pagination*/


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value="/dashtrain")
    public ModelAndView enteringIntoAdminTrain(HttpServletRequest req) {
        Map<String, Object> pag = controllerService.trainPagination();
        List<Train> trains = (List<Train>)pag.get("trains");
        trains = trains.subList(0, TRAIN_NUM_ON_PAGE);
        Map<String, Object> modelMap = new HashMap<>();
        modelMap.put("trains", trains);
        modelMap.put("trainPages", pag.get("trainPages"));
        if(req.getParameter("success") != null) {
            modelMap.put("success", true);
        }
        if(req.getParameter("deleted") != null) {
            modelMap.put("successdelete", true);
        }
        if(req.getParameter("train") != null) {
            modelMap.put("trainexist", true);
        }
        if(req.getParameter("systemError") != null) {
            modelMap.put("systemError", true);
        }
        return new ModelAndView("dashtrain", "model", modelMap);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value="/dashtrain/{count}")
    public ModelAndView trainPagination(@PathVariable(value = "count") int pageNum) {
        Map<String, Object> pag = controllerService.trainPagination();
        List<Train> trains = (List<Train>)pag.get("trains");
        if (pageNum != (int)pag.get("trainPages"))
            trains = trains.subList((pageNum - 1) * TRAIN_NUM_ON_PAGE, (pageNum - 1) * TRAIN_NUM_ON_PAGE + TRAIN_NUM_ON_PAGE);
        else
            trains = trains.subList((pageNum - 1) * TRAIN_NUM_ON_PAGE, trains.size());

        Map<String, Object> modelMap = new HashMap<>();
        modelMap.put("trains", trains);
        modelMap.put("trainPages", pag.get("trainPages"));
        return new ModelAndView("dashtrain", "model", modelMap);
    }

    /** Block for
           admin panel
                station pagination*/

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value="/dashstation")
    public ModelAndView enteringIntoAdminStation(HttpServletRequest req) {
        Map<String, Object> pag = controllerService.stationPagination(1);
        List<Station> stations = (List<Station>)pag.get("stations");
        Map<String, Object> modelMap = new HashMap<>();
        modelMap.put("stations", stations);
        modelMap.put("stationPages", pag.get("stationPages"));
        if(req.getParameter("systemError") != null) {
            modelMap.put("systemError", true);
        }
        return new ModelAndView("dashstation", "model", modelMap);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value="/dashstation/{stcount}")
    public ModelAndView stationPagination(@PathVariable(value = "stcount") int stationNum) {
        Map<String, Object> pag = controllerService.stationPagination(stationNum);
        List<Station> stations = (List<Station>)pag.get("stations");
        Map<String, Object> modelMap = new HashMap<>();
        modelMap.put("stations", stations);
        modelMap.put("stationPages", pag.get("stationPages"));
        return new ModelAndView("dashstation", "model", modelMap);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/passengers/{id}")
    public ModelAndView passengers(@PathVariable(value = "id") Long id) {
        try {
            List<Ticket> tickets = ticketService.getByTrain(trainService.findById(id));
            return new ModelAndView("passengers", "ticketlist", tickets);
        } catch (Exception ex) {
            log.error("SYSTEM EXCEPTION", ex);
            return new ModelAndView("redirect:/dashtrain", "systemError", true);
        }
    }

}