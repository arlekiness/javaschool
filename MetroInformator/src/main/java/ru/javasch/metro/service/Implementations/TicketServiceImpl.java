package ru.javasch.metro.service.Implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javasch.metro.DAO.Interfaces.StationDAO;
import ru.javasch.metro.DAO.Interfaces.TicketDAO;
import ru.javasch.metro.DAO.Interfaces.TrainDAO;
import ru.javasch.metro.DTO.ScheduleDTO;
import ru.javasch.metro.DTO.TicketDTO;
import ru.javasch.metro.configuration.constants.Utils;
import ru.javasch.metro.model.Station;
import ru.javasch.metro.model.Ticket;
import ru.javasch.metro.model.Train;
import ru.javasch.metro.service.Interfaces.StationService;
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

    @Autowired
    StationService stationService;

    @Autowired
    TrainDAO trainDAO;

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
    public void isFreeSeats(TicketDTO ticketDTO) throws ParseException {
        Train train = trainDAO.findByName(ticketDTO.getTrainName());
        Station station = stationDAO.findByName(ticketDTO.getStationBegin());
        Date date = Utils.parseToDateTime(ticketDTO.getDateDeparture());

        List<Ticket> tickets = ticketDAO.getByStationDateTrain(station, date, train);
        System.out.println(tickets);
    }

//    @Override
//    public TicketDTO formFinalTicketDTO(TicketDTO ticketDTO, List<Station> stations) {
//
//
//        return ticketDTO;
//    }
}
