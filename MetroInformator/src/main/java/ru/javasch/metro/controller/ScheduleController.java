package ru.javasch.metro.controller;

import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.javasch.metro.model.Schedule;
import ru.javasch.metro.service.interfaces.ScheduleService;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ************************************************
 * CONTROLLER FOR VIEWING SCHEDULES
 * ************************************************
 */

@Controller
@Log4j
public class ScheduleController {
    @Autowired
    private ScheduleService scheduleService;

    @GetMapping("/schedule")
    public String scheduleMap() {
        return "schedule";
    }

    /**FORM SCHEDULE TABLE FOR USER BY STATION AND DATE
     *
     * @param stationName
     * @param date
     * @return
     */

    @PostMapping("/schedule")
    public ModelAndView stationSchedule(@RequestParam(value = "start") String stationName,
                                        @RequestParam(value = "date") String date) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            List<Schedule> sch = scheduleService.getAllTrainsOnStation(stationName, date);
            if (sch.size() == 0) {
                modelMap.put("noTrains", "true");
                return new ModelAndView("schedule", "model", modelMap);
            }
            modelMap.put("showSchedule", "true");
            modelMap.put("scheduleList", sch);
            return new ModelAndView("scheduletable", "model", modelMap);
        } catch (ParseException ex) {
            log.info("PARSE EXCEPTION inside schedule form");
            modelMap.put("parseException", "true");
            return new ModelAndView("schedule", "model", modelMap);
        }
    }
}
