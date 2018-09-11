package ru.javasch.metro.service.implementations;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class SecureService {

    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public void logOut(HttpServletRequest request, HttpServletResponse response) {
        Authentication authentication = getAuthentication();
        if (authentication != null)
            new SecurityContextLogoutHandler().logout(request, response, authentication);
    }
}
