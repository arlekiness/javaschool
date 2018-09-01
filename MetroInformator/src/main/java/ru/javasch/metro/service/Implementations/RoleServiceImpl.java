package ru.javasch.metro.service.Implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javasch.metro.DAO.Interfaces.RoleDAO;
import ru.javasch.metro.model.Role;
import ru.javasch.metro.service.Interfaces.RoleService;

import javax.transaction.Transactional;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDAO roleDAO;

    @Override
    @Transactional
    public Role getRole() {
        System.out.println(roleDAO.getRole());return roleDAO.getRole();
    }
}
