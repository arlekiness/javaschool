package ru.javasch.metro.service.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javasch.metro.dao.interfaces.RoleDAO;
import ru.javasch.metro.model.Role;
import ru.javasch.metro.service.interfaces.RoleService;

import javax.transaction.Transactional;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDAO roleDAO;

    @Override
    @Transactional
    public Role getRole() {
        return roleDAO.getRole();
    }
}
