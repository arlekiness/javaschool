package ru.javasch.metro.controller;

import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.javasch.metro.service.implementations.SecureService;
import ru.javasch.metro.service.interfaces.UserService;
import ru.javasch.metro.utils.URLs;
import ru.javasch.metro.utils.VIEWs;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * ************************************************
 * CONTROLLER FOR STANDART USER OPERATIONS SUCH
 * LOGIN, REGISTRATION, LOGOUT
 * ************************************************
 */

@Controller
@Log4j
public class UserController {
    @Autowired
    private SecureService secureService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = URLs.REGISTRATION)
    public String registration() {
        return VIEWs.REGISTRATION;
    }

    @PostMapping(URLs.REGISTRATION)
    public ModelAndView registration(@RequestParam(value = "firstName") String firstName,
                                     @RequestParam(value = "lastName") String lastName,
                                     @RequestParam(value = "login") String login,
                                     @RequestParam(value = "password") String password,
                                     @RequestParam(value = "phone") String phone) throws IOException, MessagingException {
        userService.registration(firstName, lastName, login, password, phone);
        return new ModelAndView(VIEWs.LOGIN, "allgood", true);
    }


    @RequestMapping(value = URLs.LOGIN)
    public String login() {
        return VIEWs.LOGIN;
    }

    @RequestMapping(value = URLs.EMPTY_URL)
    public String loginRedirect() {
        return VIEWs.SCHEDULE;
    }

    @RequestMapping(value = URLs.HOME)
    public String home() {
        return VIEWs.SCHEDULE;
    }

    @RequestMapping(value = URLs.LOGOUT)
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        secureService.logOut(request, response);
        return VIEWs.LOGIN;
    }


}

