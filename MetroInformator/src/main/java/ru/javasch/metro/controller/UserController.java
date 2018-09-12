package ru.javasch.metro.controller;

import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.javasch.metro.exception.RuntimeBusinessLogicException;
import ru.javasch.metro.service.implementations.SecureService;
import ru.javasch.metro.service.interfaces.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

@Controller
@Log4j
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
        catch (RuntimeBusinessLogicException ex) {
            ModelAndView model = new ModelAndView();
            model.setViewName("registration");
            model.addObject("UserControllerException", ex);
            System.out.println(model.isEmpty());
            return model;
        }
        catch (Exception ex) {

            System.out.println(ex.getCause());
            return new ModelAndView("registration");
        }
    }


    @RequestMapping(value = "/login")
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/")
    public String loginRedirect() { return "stationscheme"; }

    @RequestMapping(value = "/home")
    public String home() { return "stationscheme"; }

    @RequestMapping(value = "/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        secureService.logOut(request, response);
        return "login";
    }


}

