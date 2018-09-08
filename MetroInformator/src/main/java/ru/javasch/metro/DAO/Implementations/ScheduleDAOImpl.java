package ru.javasch.metro.DAO.Implementations;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.javasch.metro.DAO.Interfaces.ScheduleDAO;
import ru.javasch.metro.model.*;
import ru.javasch.metro.service.Interfaces.StationService;

import java.util.Date;
import java.util.List;

@Repository
public class ScheduleDAOImpl<E extends Schedule> extends GenericDAOImpl<E> implements ScheduleDAO<E> {
    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    StationService stationService;

    @Override
    public List getByStation(Station station) {
        return sessionFactory.getCurrentSession()
                .createQuery("from Schedule " +
                        "where station = :station " +
                        "order by dateArrival asc ")
                .setParameter("station", station)
                .getResultList();
    }

    public Schedule findByTrainAndStation (Train train, Station station) {
        return (Schedule) sessionFactory.getCurrentSession()
                .createQuery("from Schedule where train = :train and station = :station")
                .setParameter("train", train)
                .setParameter("station", station)
                .uniqueResult();
    }

    @Override
    public List getByStationsAndDate(Station stationBegin, Station endPointStation, Date date, Date now) {

        return sessionFactory.getCurrentSession()
                .createQuery("from Schedule " + "where station = :stationBegin " + " and dateDeparture > :dateNow" + " and dateDeparture > :date" +
                                " and year(dateDeparture) = year(:date)" + " and month(dateDeparture) = month(:date)" +
                                " and day(dateDeparture) = day(:date)" +
                                " and endPointStation = :endPointStation")
                .setParameter("stationBegin", stationBegin)
                .setParameter("endPointStation", endPointStation)
                .setParameter("date", date)
                .setParameter("dateNow", now)
                .getResultList();
    }

}
