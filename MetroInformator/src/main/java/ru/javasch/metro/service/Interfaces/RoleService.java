package ru.javasch.metro.service.Interfaces;

import org.springframework.stereotype.Service;
import ru.javasch.metro.model.Role;

@Service
public interface RoleService {

    Role getRole();
}
