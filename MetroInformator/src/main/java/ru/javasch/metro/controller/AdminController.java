package ru.javasch.metro.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminController {

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value="/adminFunctions")
    public String enteringIntoAdmin() {
        return "adminka";
    }
}
