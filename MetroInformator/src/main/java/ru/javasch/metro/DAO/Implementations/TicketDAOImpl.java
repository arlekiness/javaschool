package ru.javasch.metro.DAO.Implementations;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.javasch.metro.DAO.Interfaces.StationDAO;
import ru.javasch.metro.DAO.Interfaces.TicketDAO;
import ru.javasch.metro.DTO.TicketDTO;
import ru.javasch.metro.model.Station;
import ru.javasch.metro.model.Ticket;
import ru.javasch.metro.model.Train;

import java.util.Date;
import java.util.List;

@Repository
public class TicketDAOImpl<E extends Ticket> extends GenericDAOImpl<E> implements TicketDAO<E> {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Ticket> getByStationAndDate (Station station, Date date) {
        return sessionFactory.getCurrentSession()
                .createQuery("from Ticket where stationBegin = :station " +
                        "and year(ticketDate) = year(:date)" + "and month(ticketDate) = month(:date)" + "and day(ticketDate) = day(:date)")
                .setParameter("station", station)
                .setParameter("date", date)
                .getResultList();
    }

    @Override
    public List<Ticket> getByStation(Station station) {
        return sessionFactory.getCurrentSession()
                .createQuery("from Ticket where stationBegin = :station")
                .setParameter("station", station)
                .getResultList();
    }

    @Override
    public List<Ticket> getByStationDateTrain (Station station, Date date, Train train) {
        System.out.println(station);
        System.out.println(date);
        System.out.println(train);
        return sessionFactory.getCurrentSession()
                .createQuery("from Ticket as t where :station.numberOnBranch between t.stationBegin.numberOnBranch and t.stationEnd.numberOnBranch" +
                        "and year(ticketDate) = year(:date)" + "and month(ticketDate) = month(:date)" + "and day(ticketDate) = day(:date)" +
                        "and Train = :train")
                .setParameter("station", station)
                .setParameter("date", date)
                .setParameter("train", train)
                .getResultList();
    }
}
