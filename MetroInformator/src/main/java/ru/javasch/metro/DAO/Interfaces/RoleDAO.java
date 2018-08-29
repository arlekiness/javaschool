package ru.javasch.metro.DAO.Interfaces;

import org.springframework.stereotype.Repository;
import ru.javasch.metro.model.Role;

@Repository
public interface RoleDAO {

    Role getRole();
}
