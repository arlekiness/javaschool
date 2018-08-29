package ru.javasch.metro.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.javasch.metro.configuration.constants.URLS;
import ru.javasch.metro.configuration.constants.Views;
import ru.javasch.metro.model.Station;
import ru.javasch.metro.service.Interfaces.StationService;

import java.util.List;

@Controller
public class ViewController {

    @Autowired
    StationService service;

    @RequestMapping(value =URLS.INITIAL_PAGE)
    public String login() {
        return Views.WELCOME;
    }

    @RequestMapping(value = URLS.HOME_PAGE)
    public String home() {
        return Views.WELCOME;
    }

    @RequestMapping(value = URLS.STATION_PAGE)
    public String listStations(ModelMap model) {
        List<Station> station = service.getAllStations();
        model.addAttribute("station", station);
        return "stations";
    }


}
