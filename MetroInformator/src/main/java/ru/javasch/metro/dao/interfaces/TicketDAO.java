package ru.javasch.metro.dao.interfaces;

import ru.javasch.metro.model.Station;
import ru.javasch.metro.model.Ticket;
import ru.javasch.metro.model.Train;

import java.util.Date;
import java.util.List;

public interface TicketDAO<E extends Ticket> extends GenericDAO<E> {
    public List<Ticket> getByStationAndDate (Station station, Date date);
    public List<Ticket> getByStation(Station station);
    public List<Ticket> getByStationDateTrain (Station beginStation, Station endStation, Date date, Train train);
    public List<Ticket> findTicketsByTrain(Train train);
}
