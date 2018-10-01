package ru.javasch.metro.dao.interfaces;

import ru.javasch.metro.model.Station;
import ru.javasch.metro.model.Ticket;
import ru.javasch.metro.model.Train;
import ru.javasch.metro.model.User;

import java.util.Date;
import java.util.List;

public interface TicketDAO<E extends Ticket> extends GenericDAO<E> {
    List<Ticket> getByStationAndDate(Station station, Date date);
    List<Ticket> getByStation(Station station);
    List<Ticket> getByStationDateTrain(Station beginStation, Station endStation, Date date, Train train);
    List<Ticket> findAllInvalidTickets();
    List<Ticket> findAllUserTickets(User user);
    List<Ticket> getByTrain(Train train);
    List<Ticket> getTicketsOnCurrentDate();
}
