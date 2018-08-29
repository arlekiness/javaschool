package ru.javasch.metro.service.Interfaces;

import org.springframework.security.core.userdetails.UserDetails;

public interface UserDetailService {
    public UserDetails loadUserByUsername(String username);
}
