package ru.javasch.metro.DAO.Interfaces;

import org.springframework.stereotype.Repository;
import ru.javasch.metro.model.Station;
import ru.javasch.metro.model.Ticket;

import java.util.Date;
import java.util.List;

@Repository
public interface TicketDAO<E extends Ticket> extends GenericDAO<E> {
    public List<Ticket> getByStationAndDate (Station station, Date date);
    public List<Ticket> getByStation(Station station);
}
