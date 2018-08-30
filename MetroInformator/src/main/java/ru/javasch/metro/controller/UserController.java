package ru.javasch.metro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;
import ru.javasch.metro.DTO.UserDTO;
import ru.javasch.metro.exception.BusinessLogicException;
import ru.javasch.metro.service.Implementations.SecureService;
import ru.javasch.metro.service.Interfaces.UserService;

import javax.mail.MessagingException;
import java.io.IOException;

@Controller
public class UserController {


    @Autowired
    private UserService userService;

    @Autowired
    private SecureService secureService;


    @PostMapping("/registration")
    public ModelAndView registration(@RequestBody UserDTO userDTO) throws BusinessLogicException, MessagingException, IOException {
        userService.registration(userDTO);
        return new ModelAndView("redirect:/login");
    }
}
