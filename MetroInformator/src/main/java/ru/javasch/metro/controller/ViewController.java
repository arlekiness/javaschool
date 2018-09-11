package ru.javasch.metro.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javasch.metro.service.interfaces.StationService;

@Controller
public class ViewController {

    @Autowired
    StationService service;



}
