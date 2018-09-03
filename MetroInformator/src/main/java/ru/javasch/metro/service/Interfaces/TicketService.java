package ru.javasch.metro.service.Interfaces;

import org.springframework.stereotype.Service;
import ru.javasch.metro.DTO.TicketDTO;
import ru.javasch.metro.model.Ticket;

import java.text.ParseException;
import java.util.List;

@Service
public interface TicketService {
    public List<Ticket> getTicketsByStationAndDate (String stationName, String date) throws ParseException;

    public List<Ticket> getTicketsByStation (String stationName) throws ParseException;

    TicketDTO formBeginTicketDTO(String stationName, String dateDeparture, String trainName, String endPointStation, String userId);

    public void isFreeSeats(TicketDTO ticketDTO) throws ParseException;
}

