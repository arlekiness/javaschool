package ru.javasch.metro.service.Implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javasch.metro.DAO.Interfaces.StationDAO;
import ru.javasch.metro.DAO.Interfaces.TicketDAO;
import ru.javasch.metro.DAO.Interfaces.TrainDAO;
import ru.javasch.metro.DAO.Interfaces.UserDAO;
import ru.javasch.metro.DTO.TicketDTO;
import ru.javasch.metro.configuration.constants.Utils;
import ru.javasch.metro.exception.BusinessLogicException;
import ru.javasch.metro.model.Station;
import ru.javasch.metro.model.Ticket;
import ru.javasch.metro.model.Train;
import ru.javasch.metro.model.User;
import ru.javasch.metro.model.Branch;
import ru.javasch.metro.service.Interfaces.StationService;
import ru.javasch.metro.service.Interfaces.TicketService;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.util.Date;
import java.util.List;


@Service
public class TicketServiceImpl implements TicketService {
    @Autowired
    TicketDAO ticketDAO;

    @Autowired
    StationDAO stationDAO;

    @Autowired
    StationService stationService;

    @Autowired
    TrainDAO trainDAO;

    @Autowired
    UserDAO userDAO;


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



    @Override
    public TicketDTO formBeginTicketDTO(String stationName, String dateDeparture, String trainName, String endPointStation, String userId) {
        TicketDTO ticketDTO = new TicketDTO();
        ticketDTO.setStationBegin(stationName);
        ticketDTO.setDateDeparture(dateDeparture);
        ticketDTO.setTrainName(trainName);
        ticketDTO.setEndPointStation(endPointStation);
        ticketDTO.setUserId(userId);

        return ticketDTO;
    }

    @Override
    @Transactional
    public int occupiedSeats(TicketDTO ticketDTO) throws ParseException, BusinessLogicException {
        Train train = trainDAO.findByName(ticketDTO.getTrainName());
        Station beginStation = stationDAO.findByName(ticketDTO.getStationBegin());
        Station endStation = stationDAO.findByName(ticketDTO.getStationEnd());
        Date date = Utils.parseToDateTime(ticketDTO.getDateDeparture());

        List<Ticket> tickets = ticketDAO.getByStationDateTrain(beginStation, endStation, date, train);
        return tickets.size();
    }

    @Override
    @Transactional
    public void addTicketInSystem(TicketDTO ticketDTO) throws ParseException {
        User user = (User)userDAO.getById(Long.parseLong(ticketDTO.getUserId()));
        Train train = trainDAO.findByName(ticketDTO.getTrainName());
        Station stationBegin = stationDAO.findByName(ticketDTO.getStationBegin());
        Station stationEnd = stationDAO.findByName(ticketDTO.getStationEnd());
        Date date = Utils.parseToDateTime(ticketDTO.getDateDeparture());
        Integer price = Math.abs(stationBegin.getNumberOnBranch() - stationEnd.getNumberOnBranch()) * 100;
        Branch branch = stationBegin.getBranch();
        Ticket ticket = new Ticket();
        ticket.setUser(user);
        ticket.setTrain(train);
        ticket.setStationBegin(stationBegin);
        ticket.setStationEnd(stationEnd);
        ticket.setTicketDate(date);
        ticket.setPrice(price);
        ticket.setBranch(branch);
        ticketDAO.add(ticket);
    }
}
