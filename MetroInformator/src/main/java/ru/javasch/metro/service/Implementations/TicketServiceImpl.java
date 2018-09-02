package ru.javasch.metro.service.Implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javasch.metro.DAO.Interfaces.StationDAO;
import ru.javasch.metro.DAO.Interfaces.TicketDAO;
import ru.javasch.metro.DTO.ScheduleDTO;
import ru.javasch.metro.configuration.constants.Utils;
import ru.javasch.metro.model.Station;
import ru.javasch.metro.model.Ticket;
import ru.javasch.metro.service.Interfaces.TicketService;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Service
public class TicketServiceImpl implements TicketService {
    @Autowired
    TicketDAO ticketDAO;

    @Autowired
    StationDAO stationDAO;

    @Override
    @Transactional
    public List<Ticket> getTicketsByStationAndDate (String stationName, String date) throws ParseException {
        Station station = stationDAO.findByName(stationName);
        Date newDate = Utils.parseToDate(date);
        return ticketDAO.getByStationAndDate(station, newDate);
    }

    @Override
    @Transactional
    public List<Ticket> getTicketsByStation (String stationName) throws ParseException {
        Station station = stationDAO.findByName(stationName);
        return ticketDAO.getByStation(station);
    }
}
