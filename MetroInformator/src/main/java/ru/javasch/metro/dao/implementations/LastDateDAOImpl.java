package ru.javasch.metro.dao.implementations;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.javasch.metro.dao.interfaces.LastDateDAO;
import ru.javasch.metro.model.LastDateSchedule;

import java.util.Calendar;
import java.util.Date;

@Repository
public class LastDateDAOImpl implements LastDateDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public LastDateSchedule getLastDate() {
        LastDateSchedule date = (LastDateSchedule)sessionFactory.getCurrentSession()
                .createQuery("FROM LastDateSchedule where id = 2")
                .uniqueResult();
        return date;
    }

    @Override
    public LastDateSchedule getFromDate() {
        LastDateSchedule date = (LastDateSchedule)sessionFactory.getCurrentSession()
                .createQuery("FROM LastDateSchedule where id = 1")
                .uniqueResult();
        return date;
    }
}
