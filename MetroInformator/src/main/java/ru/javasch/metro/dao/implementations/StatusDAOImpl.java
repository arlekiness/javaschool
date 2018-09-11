package ru.javasch.metro.dao.implementations;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.javasch.metro.dao.interfaces.StatusDAO;
import ru.javasch.metro.model.Status;

@Repository
public class StatusDAOImpl implements StatusDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Status getWorkStatus() {
        return (Status) sessionFactory.getCurrentSession()
                .createQuery("FROM Status where statusName = :STATUS")
                .setParameter("STATUS", "WORKED")
                .uniqueResult();
    }

    @Override
    public Status getCloseStatus() {
        return (Status) sessionFactory.getCurrentSession()
                .createQuery("FROM Status where statusName = :STATUS")
                .setParameter("STATUS", "CLOSED")
                .uniqueResult();
    }

    @Override
    public Status getDestroyedStatus() {
        return (Status) sessionFactory.getCurrentSession()
                .createQuery("FROM Status where statusName = :STATUS")
                .setParameter("STATUS", "DESTROYED BY MUTANTS")
                .uniqueResult();
    }
}
