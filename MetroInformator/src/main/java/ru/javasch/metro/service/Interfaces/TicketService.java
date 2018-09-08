package ru.javasch.metro.service.Interfaces;

import org.springframework.stereotype.Service;
import ru.javasch.metro.DTO.TicketDTO;
import ru.javasch.metro.exception.BusinessLogicException;
import ru.javasch.metro.model.Schedule;
import ru.javasch.metro.model.Station;
import ru.javasch.metro.model.Ticket;

import java.text.ParseException;
import java.util.List;

@Service
public interface TicketService {
    public int occupiedSeats(Schedule schedule, Station endStation) throws ParseException, BusinessLogicException;
    public void addTicketInSystem(TicketDTO ticketDTO) throws ParseException;
    public String formMessageAboutPath(List<List<Station>> segments);
    public List<Schedule>  formFirstTicket(List<List<Station>> segments, String date) throws ParseException;
    public List<List<Ticket>> formTicketChains(List<List<Station>> pathSegment, List<Schedule> schedule);
}

