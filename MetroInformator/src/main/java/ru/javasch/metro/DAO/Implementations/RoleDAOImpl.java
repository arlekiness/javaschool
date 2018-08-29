package ru.javasch.metro.DAO.Implementations;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.javasch.metro.DAO.Interfaces.RoleDAO;
import ru.javasch.metro.model.Role;

@Repository
public class RoleDAOImpl implements RoleDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Role getRole() {
        return (Role) sessionFactory.getCurrentSession()
                .createQuery("FROM Role where type = :ROLE")
                .setParameter("ROLE", "USER")
                .uniqueResult();
    }
}
