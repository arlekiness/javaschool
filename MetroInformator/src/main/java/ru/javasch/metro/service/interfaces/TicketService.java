package ru.javasch.metro.service.interfaces;

import org.springframework.stereotype.Service;
import ru.javasch.metro.model.Schedule;
import ru.javasch.metro.model.Station;
import ru.javasch.metro.model.Ticket;

import javax.mail.MessagingException;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

@Service
public interface TicketService {
    int occupiedSeats(Schedule schedule, Station endStation) throws ParseException;
    String formMessageAboutPath(List<List<Station>> segments);
    List<Schedule>  formFirstTicket(List<List<Station>> segments, String date) throws ParseException;
    List<List<Ticket>> formTicketChains(List<List<Station>> pathSegment, List<Schedule> schedule);
    void registrateTicketsInSystem(List<Ticket> ticket, String userName);
    List<Ticket> invalidateNonValidTickets();
    void sendInvalidateMessages(List<Ticket> tickets) throws IOException, MessagingException;
    public List<Ticket> findAllTicketsByUser();
}

