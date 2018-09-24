package ru.javasch.metro.dao.implementations;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.javasch.metro.dao.interfaces.TicketDAO;
import ru.javasch.metro.exception.RuntimeBusinessLogicException;
import ru.javasch.metro.model.Station;
import ru.javasch.metro.model.Ticket;
import ru.javasch.metro.model.Train;
import ru.javasch.metro.model.User;

import java.util.Date;
import java.util.List;

@Repository
public class TicketDAOImpl<E extends Ticket> extends GenericDAOImpl<E> implements TicketDAO<E> {

    @Override
    public List<Ticket> getByStationAndDate (Station station, Date date) {
        return sessionFactory.getCurrentSession()
                .createQuery("from Ticket where stationBegin = :station " +
                        "and year(ticketDateDeparture) = year(:date)" + "and month(ticketDate) = month(:date)" + "and day(ticketDate) = day(:date)")
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
    public List<Ticket> getByStationDateTrain (Station beginStation, Station endStation, Date date, Train train) {
        Integer beginNumberOnBranch = beginStation.getNumberOnBranch();
        Integer endNumberOnBranch = endStation.getNumberOnBranch();

        if (beginNumberOnBranch < endNumberOnBranch)
            return sessionFactory.getCurrentSession()
                    .createQuery("from Ticket where train = :train and " +
                            "year(ticketDateDeparture) = year(:date) and " +
                            "month(ticketDateDeparture) = month(:date) and " +
                            "day(ticketDateDeparture) = day(:date) and " +
                            "((:beginNumberOnBranch = stationBegin.numberOnBranch) or " +
                            " (stationBegin.numberOnBranch < :beginNumberOnBranch and stationEnd.numberOnBranch > :beginNumberOnBranch) or " +
                              "(stationBegin.numberOnBranch > :beginNumberOnBranch and stationBegin.numberOnBranch < :endNumberOnBranch))")
                    .setParameter("train", train)
                    .setParameter("date", date)
                    .setParameter("beginNumberOnBranch", beginNumberOnBranch)
                    .setParameter("endNumberOnBranch", endNumberOnBranch)
                    .getResultList();
        else if (beginNumberOnBranch > endNumberOnBranch)
            return sessionFactory.getCurrentSession()
                    .createQuery("from Ticket where train = :train and " +
                            "year(ticketDateDeparture) = year(:date) and " +
                            "month(ticketDateDeparture) = month(:date) and " +
                            "day(ticketDateDeparture) = day(:date) and " +
                            "((:beginNumberOnBranch = stationBegin.numberOnBranch) or " +
                            " (stationBegin.numberOnBranch > :beginNumberOnBranch and stationEnd.numberOnBranch < :beginNumberOnBranch) or " +
                            "(stationBegin.numberOnBranch < :beginNumberOnBranch and stationBegin.numberOnBranch > :endNumberOnBranch))")
                    .setParameter("train", train)
                    .setParameter("date", date)
                    .setParameter("beginNumberOnBranch", beginNumberOnBranch)
                    .setParameter("endNumberOnBranch", endNumberOnBranch)
                    .getResultList();
        else
            throw new RuntimeBusinessLogicException("You wanna buy zero ticket?");
    }

    @Override
    public List <Ticket> findAllInvalidTickets() {
        return (List <Ticket>) sessionFactory.getCurrentSession()
                .createQuery("from Ticket where train = null and valid = :valid")
                .setParameter("valid", "VALID")
                .getResultList();
    }

    @Override
    public List<Ticket> findAllUserTickets(User user) {
        return (List <Ticket>) sessionFactory.getCurrentSession()
                .createQuery("from Ticket where user = :user")
                .setParameter("user", user)
                .getResultList();
    }

    @Override
    public List<Ticket> getByTrain(Train train) {
        Date date = new Date();
        return (List <Ticket>) sessionFactory.getCurrentSession()
                .createQuery("from Ticket where train = :train and " +
                        " ticketDateDeparture > :date")
                .setParameter("train", train)
                .setParameter("date", date)
                .getResultList();
    }
}
