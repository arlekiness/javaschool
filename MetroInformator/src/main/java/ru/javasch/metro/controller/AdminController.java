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

import javax.servlet.http.HttpServletRequest;
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
        } catch (RuntimeBusinessLogicException ex) {
            log.info("EXCEPTION: " + ex.getError());
            return new ModelAndView("redirect:/dashtrain", "train", true);
        } catch (Exception ex) {
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
        } catch (RuntimeBusinessLogicException ex) {
            log.info("EXCEPTION: " + ex.getError());
            return new ModelAndView("redirect:/dashtrain", "systemError", true);
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
        try {
            stationService.closeStation(stationName);
            String color = stationService.findByName(stationName).getBranch().getColor();
            if (color.equals("RED"))
                return new ModelAndView("redirect:/dashstation");
            else if (color.equals("BLUE"))
                return new ModelAndView("redirect:/dashstation/2");
            else if (color.equals("GREEN"))
                return new ModelAndView("redirect:/dashstation/3");
            else if (color.equals("ORANGE"))
                return new ModelAndView("redirect:/dashstation/4");
            else
                return new ModelAndView("redirect:/dashstation/5");
        } catch (RuntimeBusinessLogicException ex) {
            log.info("EXCEPTION: " + ex.getError());
            return new ModelAndView("redirect:/dashstation");
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/openStation/{stationName}")
    public ModelAndView openStation(@PathVariable(value = "stationName") String stationName) {
        try {
            stationService.openStation(stationName);
            String color = stationService.findByName(stationName).getBranch().getColor();
            if (color.equals("RED"))
                return new ModelAndView("redirect:/dashstation");
            else if (color.equals("BLUE"))
                return new ModelAndView("redirect:/dashstation/2");
            else if (color.equals("GREEN"))
                return new ModelAndView("redirect:/dashstation/3");
            else if (color.equals("ORANGE"))
                return new ModelAndView("redirect:/dashstation/4");
            else
                return new ModelAndView("redirect:/dashstation/5");
        } catch (RuntimeBusinessLogicException ex) {
            log.info("EXCEPTION: " + ex.getError());
            return new ModelAndView("redirect:/dashstation");
        }
    }

    /** Block for
                admin panel
                    train pagination*/


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value="/dashtrain")
    public ModelAndView enteringIntoAdminTrain(HttpServletRequest req) {
        Map<String, Object> pag = controllerService.trainpagination();
        List<Train> trains = (List<Train>)pag.get("trains");
        trains = trains.subList(0, 20);
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
        Map<String, Object> pag = controllerService.trainpagination();
        List<Train> trains = (List<Train>)pag.get("trains");
        if (pageNum != (int)pag.get("trainPages"))
            trains = trains.subList((pageNum - 1) * 20, (pageNum - 1) * 20 + 20);
        else
            trains = trains.subList((pageNum - 1) * 20, trains.size());

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
        Map<String, Object> pag = controllerService.stationpagination();
        List<Station> stations = (List<Station>)pag.get("stations");
        stations = stations.subList(0, 19);
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
        Map<String, Object> pag = controllerService.stationpagination();
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
        Map<String, Object> modelMap = new HashMap<>();
        modelMap.put("stations", stations);
        modelMap.put("stationPages", pag.get("stationPages"));
        return new ModelAndView("dashstation", "model", modelMap);
    }

}


//    @PreAuthorize("hasRole('ROLE_ADMIN')")
//    @RequestMapping(value="/dash")
//    public ModelAndView enteringIntoAdmin(HttpServletRequest req) {
//        Map<String, Object> pag = controllerService.pagination();
//        List<Train> trains = (List<Train>)pag.get("trains");
//        List<Station> stations = (List<Station>)pag.get("stations");
//        trains = trains.subList(0, 20);
//        stations = stations.subList(0, 19);
//        Map<String, Object> modelMap = new HashMap<>();
//        modelMap.put("trains", trains);
//        modelMap.put("stations", stations);
//        modelMap.put("trainPages", pag.get("trainPages"));
//        modelMap.put("stationPages", pag.get("stationPages"));
//        if(req.getParameter("success") != null) {
//            modelMap.put("success", true);
//        }
//        if(req.getParameter("deleted") != null) {
//            modelMap.put("successdelete", true);
//        }
//        if(req.getParameter("train") != null) {
//            modelMap.put("trainexist", true);
//        }
//        if(req.getParameter("systemError") != null) {
//            modelMap.put("systemError", true);
//        }
//        return new ModelAndView("dash", "model", modelMap);
//    }