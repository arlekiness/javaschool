package ru.javasch.metro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.javasch.metro.configuration.constants.URLS;
import ru.javasch.metro.model.Station;
import ru.javasch.metro.service.Interfaces.StationService;

@Controller
public class StationController {
    @Autowired
    StationService service;

    @RequestMapping(value="/addStation")
    public String addStationForm() {
        return "addStationForm";
    }

    @RequestMapping(value="/addStationPlus", method = RequestMethod.POST)
    public ModelAndView addStation(@RequestParam("name") String name, @RequestParam("color") String color) {
        System.out.println(name + " " + color);
        Station station = new Station();
        station.setName(name);
        station.setColor(color);
        System.out.println(station.toString());
        service.add(station);
        return new ModelAndView("redirect:/station");
    }

    @RequestMapping(value="/deleteStation", method = RequestMethod.GET)
    public ModelAndView deleteStation(@RequestParam("name") String name) {
        System.out.println("deleteStation");
        service.delete(name);
        return new ModelAndView("redirect:/station");
    }


}
