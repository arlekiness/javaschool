package ru.javasch.metro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.javasch.metro.service.Implementations.SecureService;
import ru.javasch.metro.service.Interfaces.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class UserController {
    @Autowired
    private SecureService secureService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public ModelAndView registration(@RequestParam(value="firstName") String firstName,
                               @RequestParam(value="lastName") String lastName,
                               @RequestParam(value="login") String login,
                               @RequestParam(value="password") String password) {
        try {
            userService.registration(firstName, lastName, login, password);
            ModelAndView model = new ModelAndView();
            model.addObject("Allgood", "Success!");
            model.setViewName("redirect:/login");
            return model;
        }
        catch (Exception ex) {
            ModelAndView model = new ModelAndView();
            model.addObject("Wrong", "Something wrong");
            model.setViewName("redirect:/registration");
            return model;
        }
    }

    @RequestMapping(value = "/login")
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/")
    public String loginRedirect() { return "login"; }

    @RequestMapping(value = "/home")
    public String home() { return "stationscheme"; }

    @RequestMapping(value = "/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        secureService.logOut(request, response);
        return "login";
    }


}

