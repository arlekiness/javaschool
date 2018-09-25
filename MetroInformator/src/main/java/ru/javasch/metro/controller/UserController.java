package ru.javasch.metro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import lombok.extern.log4j.Log4j;
import ru.javasch.metro.exception.ErrorCode;
import ru.javasch.metro.exception.RuntimeBusinessLogicException;
import ru.javasch.metro.service.implementations.SecureService;
import ru.javasch.metro.service.interfaces.UserService;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
                               @RequestParam(value="password") String password) throws IOException, MessagingException {
            userService.registration(firstName, lastName, login, password);
            return new ModelAndView("login", "allgood", true);
    }


    @RequestMapping(value = "/login")
    public String login() { return "login"; }

    @RequestMapping(value = "/")
    public String loginRedirect() { return "schedule"; }

    @RequestMapping(value = "/home")
    public String home() { return "schedule"; }

    @RequestMapping(value = "/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        secureService.logOut(request, response);
        return "login";
    }


}

