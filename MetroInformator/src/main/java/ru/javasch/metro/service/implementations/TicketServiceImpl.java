package ru.javasch.metro.service.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javasch.metro.dao.interfaces.StationDAO;
import ru.javasch.metro.dao.interfaces.TicketDAO;
import ru.javasch.metro.dao.interfaces.TrainDAO;
import ru.javasch.metro.dao.interfaces.UserDAO;
import ru.javasch.metro.exception.RuntimeBusinessLogicException;
import ru.javasch.metro.model.*;
import ru.javasch.metro.service.interfaces.ScheduleService;
import ru.javasch.metro.service.interfaces.StationService;
import ru.javasch.metro.service.interfaces.TicketService;
import ru.javasch.metro.service.interfaces.UserService;

import javax.mail.MessagingException;
import javax.transaction.Transactional;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


@Service
public class TicketServiceImpl implements TicketService {
    private static final int TRAIN_CAPACITY = 6;

    @Autowired
    TicketDAO ticketDAO;

    @Autowired
    StationDAO stationDAO;

    @Autowired
    StationService stationService;

    @Autowired
    ScheduleService scheduleService;

    @Autowired
    UserService userService;

    @Autowired
    MailService mailService;


    @Override
    @Transactional
    public int occupiedSeats(Schedule schedule, Station endStation) {
        Train train = schedule.getTrain();
        Station beginStation = schedule.getStation();
        Date date = schedule.getDateDeparture();

        List<Ticket> tickets = ticketDAO.getByStationDateTrain(beginStation, endStation, date, train);
        return tickets.size();
    }

    @Override
    @Transactional
    public String formMessageAboutPath(List<List<Station>> segments) {
        StringBuilder str = new StringBuilder("Your path: ");
        for (List<Station> stList : segments) {
            if (stList.get(0).getBranch().equals(stList.get(1).getBranch()))
                str.append(stList.get(0).getName() + " ---> " + stList.get(1).getName() + ", ");
            else
                str.append(" Transition from " + stList.get(0).getName() + " to " + stList.get(1).getName() + ", ");
        }
        String message = new String(str);
        message = message.substring(0, str.length() - 2).concat(".");
        return message;
    }

    @Override
    @Transactional
    public List<Schedule> formFirstTicket(List<List<Station>> segments, String dateForm) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = format.parse(dateForm);
        Date now = new Date();
        List<Station> stat = segments.get(0);
        List<Schedule> schedules = scheduleService.getAllSchedulesByStationDateAndPath(stat.get(0), stat.get(1), date, now);
        Iterator it = schedules.iterator();
        while (it.hasNext()) {
            Schedule sch = (Schedule) it.next();
            int occupied = this.occupiedSeats(sch, stat.get(1));
            if (occupied == sch.getTrain().getCapacity())
                it.remove();
        }
        return schedules;
    }

    @Override
    @Transactional
    public List<List<Ticket>> formTicketChains(List<List<Station>> pathSegment, List<Schedule> schedule) {
        Station endOfFirstSegment = pathSegment.get(0).get(1);
        List<List<Schedule>> schedulesChain = new ArrayList<>();
        schedulesChain.add(schedule);
        List<Schedule> endsOfFirstSegmentSchedule = new ArrayList<>();
        for (Schedule sch : schedule) {
            Schedule end = scheduleService.findByTrainAndStation(endOfFirstSegment, sch.getTrain());
            endsOfFirstSegmentSchedule.add(end);
        }

        schedulesChain.add(endsOfFirstSegmentSchedule);
        for (int i = 1; i < pathSegment.size(); i++) {
            List<Station> stat = pathSegment.get(i);
            List<Schedule> schedulesChainSeg = new ArrayList<>();
            for (Schedule sch : endsOfFirstSegmentSchedule) {
                Date chainArrivalDate = sch.getDateArrival();
                Calendar cal = Calendar.getInstance();
                cal.setTime(chainArrivalDate);
                cal.add(Calendar.MINUTE, 10);
                chainArrivalDate = cal.getTime();

                List<Schedule> schedules = scheduleService.getAllSchedulesByStationDateAndPath(stat.get(0), stat.get(1), chainArrivalDate, new Date());
                Iterator it = schedules.iterator();
                while (it.hasNext()) {
                    Schedule sched = (Schedule) it.next();
                    int occupied = this.occupiedSeats(sched, stat.get(1));
                    if (occupied == sched.getTrain().getCapacity())
                        it.remove();
                }
                if (schedules.size() > 0) {
                    Schedule needed = schedules.get(0);
                    schedulesChainSeg.add(needed);
                }
            }
            schedulesChain.add(schedulesChainSeg);
            List<Schedule> anotherOneList = new ArrayList<>();
            for (Schedule sch : schedulesChainSeg) {
                Schedule end = scheduleService.findByTrainAndStation(stat.get(1), sch.getTrain());
                anotherOneList.add(end);
            }
            schedulesChain.add(anotherOneList);
        }

        int ticketChain = schedulesChain.size();
        int ticketsInChain = ticketChain / 2;
        int fullChains = schedulesChain.get(ticketChain - 1).size();

        System.out.println(ticketChain + " " + fullChains);

        List<List<Ticket>> tickets = new ArrayList<>();
        for (int i = 0; i < fullChains - 1; i++) {
            List<Ticket> chain = new ArrayList<>();
            for (int j = 1; j < ticketChain; j += 2) {
                Ticket ticket = new Ticket();
                Schedule begin = schedulesChain.get(j - 1).get(i);
                Schedule end = schedulesChain.get(j).get(i);
                ticket.setPrice(500);
                ticket.setStationBegin(begin.getStation());
                ticket.setStationEnd(end.getStation());
                ticket.setTrain(begin.getTrain());
                ticket.setTicketDateDeparture(begin.getDateDeparture());
                ticket.setTicketDateArrival(end.getDateArrival());
                ticket.setBranch(begin.getStation().getBranch());
                ticket.setValid("VALID");
                chain.add(ticket);
            }
            tickets.add(chain);
        }

        return tickets;
    }

    @Override
    @Transactional
    public void registrateTicketsInSystem(List<Ticket> ticket, String userName) {
        User user = userService.findUserByEmail(userName);
        for (Ticket t : ticket) {
            Station beginStation = t.getStationBegin();
            Station endStation = t.getStationEnd();
            Date date = t.getTicketDateDeparture();
            Train train = t.getTrain();
            List<Ticket> tickets = ticketDAO.getByStationDateTrain(beginStation, endStation, date, train);
            if (tickets.size() < TRAIN_CAPACITY) {
                t.setUser(user);
                ticketDAO.add(t);
            } else {
                throw new RuntimeBusinessLogicException("Some of tickets was already booked. Please try new search");
            }
        }
    }

    @Override
    @Transactional
    public List<Ticket> invalidateNonValidTickets() {
        List<Ticket> tickets = ticketDAO.findAllInvalidTickets();
        System.out.println("Now here" + tickets.size());
        for (Ticket t : tickets)
            t.setValid("INVALID");
        return tickets;
    }

    @Override
    @Transactional
    public void sendInvalidateMessages(List<Ticket> tickets) throws IOException, MessagingException {
        for (Ticket t: tickets) {
            User user = t.getUser();
            Message message = Message.createInvalidateMessage(user.getLogin());
            mailService.sendMimeMessage(message);
        }
    }
}
