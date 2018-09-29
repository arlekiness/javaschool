package ru.javasch.metro.dao.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.javasch.metro.dao.interfaces.ScheduleDAO;
import ru.javasch.metro.exception.RuntimeBusinessLogicException;
import ru.javasch.metro.model.Schedule;
import ru.javasch.metro.model.Station;
import ru.javasch.metro.model.Train;
import ru.javasch.metro.service.interfaces.StationService;

import java.util.Date;
import java.util.List;

@Repository
public class ScheduleDAOImpl<E extends Schedule> extends GenericDAOImpl<E> implements ScheduleDAO<E> {

    @Autowired
    StationService stationService;

    @Override
    public List<Schedule> getByStationAndDate(Station station, Date date) {
        Date now = new Date();
        try {
            return (List<Schedule>) sessionFactory.getCurrentSession()
                    .createQuery("from Schedule " +
                            "where station = :station " + " and dateDeparture > :datenow and year(dateDeparture) = year(:date)" + " and month(dateDeparture) = month(:date)" +
                            " and day(dateDeparture) = day(:date)" +
                            " order by dateArrival asc ")
                    .setParameter("station", station)
                    .setParameter("date", date)
                    .setParameter("datenow", now)
                    .getResultList();
        } catch (Exception ex) {
            throw new RuntimeBusinessLogicException("There is no trains on that date on that station");
        }
    }

    public Schedule findByTrainAndStation(Train train, Station station, Date date) {
        return (Schedule) sessionFactory.getCurrentSession()
                .createQuery("from Schedule where train = :train and station = :station" + " and dateDeparture > :date" +
                        " and year(dateDeparture) = year(:date)" + " and month(dateDeparture) = month(:date)" +
                        " and day(dateDeparture) = day(:date)")
                .setParameter("train", train)
                .setParameter("station", station)
                .setParameter("date", date)
                .uniqueResult();
    }

    @Override
    public List getByStationsAndDate(Station stationBegin, Station endPointStation, Date date, Date now) {
        try {
            return sessionFactory.getCurrentSession()
                    .createQuery("from Schedule " + "where station = :stationBegin " + " and dateDeparture > :dateNow" + " and dateDeparture > :date" +
                            " and year(dateDeparture) = year(:date)" + " and month(dateDeparture) = month(:date)" +
                            " and day(dateDeparture) = day(:date)" +
                            " and dateDeparture > :date" +
                            " and endPointStation = :endPointStation")
                    .setParameter("stationBegin", stationBegin)
                    .setParameter("endPointStation", endPointStation)
                    .setParameter("date", date)
                    .setParameter("dateNow", now)
                    .getResultList();
        } catch (Exception ex) {
            throw new RuntimeBusinessLogicException("There is not trains");
        }
    }

    @Override
    public List<Schedule> getPastSchedules() {
        Date date = new Date();
        return (List<Schedule>)sessionFactory.getCurrentSession()
                .createQuery("from Schedule where dateArrival < :date")
                .setParameter("date", date)
                .getResultList();
    }

}
