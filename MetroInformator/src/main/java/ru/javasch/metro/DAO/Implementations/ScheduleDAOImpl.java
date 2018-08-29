package ru.javasch.metro.DAO.Implementations;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.javasch.metro.DAO.Interfaces.ScheduleDAO;
import ru.javasch.metro.model.Schedule;
import ru.javasch.metro.model.Station;

import java.util.Date;
import java.util.List;

public class ScheduleDAOImpl<E extends Schedule> extends GenericDAOImpl<E> implements ScheduleDAO<E> {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List getByDate(Date date) {
        return sessionFactory.getCurrentSession()
                .createQuery("from Schedule " +
                        "where date(dateArrival) = :date " +
                        "order by dateArrival desc ")
                .setParameter("date", date)
                .getResultList();
    }

    @Override
    public List getByStation(Station station) {
        return null;
    }

    @Override
    public List getByStationAndDate(Schedule schedule) {
        return sessionFactory.getCurrentSession()
                .createQuery("from Schedule where " +
                        "station = :station and "  +
                        "date(dateArrival) = :date " +
                        "order by dateDeparture desc ")
                .setParameter("stationArrival", schedule.getDateArrival())
                .setParameter("date", schedule.getDateDeparture())
                .getResultList();
    }
}
