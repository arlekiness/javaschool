package ru.javasch.metro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.javasch.metro.DTO.ScheduleDTO;
import ru.javasch.metro.service.Interfaces.ScheduleService;

import java.util.List;

@Controller
public class ScheduleController {
    @Autowired
    ScheduleService scheduleService;

    @RequestMapping(value="/stationList")
    public ModelAndView stationSchedule(@RequestParam(value="stationSelect") String stationName) {
        System.out.println(stationName);
        List<ScheduleDTO> sch = scheduleService.getAllTrainsOnStation(stationName);
        for (ScheduleDTO a : sch)
            System.out.println(a.getDateArrival() + " " + a.getDateDeparture() + " " + a.getStation() + " " + a.getTrainName());
        return new ModelAndView("station", "scheduleList", sch);
    }
}
