package ru.javasch.metro.dao.implementations;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.javasch.metro.dao.interfaces.RoleDAO;
import ru.javasch.metro.model.Role;

@Repository
public class RoleDAOImpl implements RoleDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Role getRole() {
        return (Role) sessionFactory.getCurrentSession()
                .createQuery("FROM Role where type = :ROLE")
                .setParameter("ROLE", "ROLE_USER")
                .uniqueResult();
    }
}
