package ru.javasch.metro.service.implementations;

import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javasch.metro.dao.interfaces.TicketDAO;
import ru.javasch.metro.exception.ErrorCode;
import ru.javasch.metro.exception.RuntimeBusinessLogicException;
import ru.javasch.metro.model.*;
import ru.javasch.metro.scheduled.ScheduledTasks;
import ru.javasch.metro.service.interfaces.ScheduleService;
import ru.javasch.metro.service.interfaces.TicketService;
import ru.javasch.metro.service.interfaces.UserService;

import javax.mail.MessagingException;
import org.springframework.transaction.annotation.Transactional;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


@Service
@Log4j
public class TicketServiceImpl implements TicketService {
    private static final int TRAIN_CAPACITY = 6;
    private static final int ENDLESS_WEIGHT = 100000;

    @Autowired
    private TicketDAO ticketDAO;

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private UserService userService;

    @Autowired
    private MailService mailService;

    @Autowired
    private SMSSenderService smsSenderService;

    /**CHECKING FOR FREE CAPACITY IN TRAIN
     *
     * @param schedule
     * @param endStation
     * @return
     */
    @Override
    @Transactional
    public int occupiedSeats(Schedule schedule, Station endStation) {
        Train train = schedule.getTrain();
        Station beginStation = schedule.getStation();
        Date date = schedule.getDateDeparture();

        List<Ticket> tickets = ticketDAO.getByStationDateTrain(beginStation, endStation, date, train);
        return tickets.size();
    }

    /**DEBUG METHOD NEEDED FOR CHECKING CORRECTNESS OF PATHFINDER WORK
     *
     * @param segments
     * @return
     */
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

    /**FORMING FIRST TICKET IN TICKET CHAIN WITH TRANSITIONS FOR USING IN TABLE
     *
     * @param segments
     * @param dateForm
     * @return
     * @throws ParseException
     */
    @Override
    @Transactional
    public List<Schedule> formFirstTicket(List<List<Station>> segments, String dateForm) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        Date date = format.parse(dateForm);
        Date now = new Date();
        List<Station> stations = segments.get(0);
        List<Schedule> schedules = scheduleService.getAllSchedulesByStationDateAndPath(stations.get(0), stations.get(1), date, now);
        Iterator it = schedules.iterator();
        while (it.hasNext()) {
            Schedule sch = (Schedule) it.next();
            int occupied = this.occupiedSeats(sch, stations.get(1));
            if (occupied == sch.getTrain().getCapacity())
                it.remove();
        }
        return schedules;
    }

    /**FORMING FULL TICKET CHAIN FOR BUYING
     *
     * @param pathSegment
     * @param schedule
     * @return
     */
    @Override
    @Transactional
    public List<List<Ticket>> formTicketChains(List<List<Station>> pathSegment, List<Schedule> schedule) {
        Date date = schedule.get(0).getDateDeparture();
        Station endOfFirstSegment = pathSegment.get(0).get(1);
        List<List<Schedule>> schedulesChain = new ArrayList<>();
        schedulesChain.add(schedule);
        List<Schedule> endsOfFirstSegmentSchedule = new ArrayList<>();
        for (Schedule sch : schedule) {
            Schedule end = scheduleService.findByTrainAndStation(endOfFirstSegment, sch.getTrain(), date);
            endsOfFirstSegmentSchedule.add(end);
        }

        schedulesChain.add(endsOfFirstSegmentSchedule);
        for (int i = 1; i < pathSegment.size(); i++) {
            List<Station> stations = pathSegment.get(i);
            List<Schedule> schedulesChainSeg = new ArrayList<>();
            for (Schedule sch : endsOfFirstSegmentSchedule) {
                Date chainArrivalDate = sch.getDateArrival();
                Calendar cal = Calendar.getInstance();
                cal.setTime(chainArrivalDate);
                cal.add(Calendar.MINUTE, 10);
                chainArrivalDate = cal.getTime();

                List<Schedule> schedules = scheduleService.getAllSchedulesByStationDateAndPath(stations.get(0), stations.get(1), chainArrivalDate, new Date());
                Iterator it = schedules.iterator();
                while (it.hasNext()) {
                    Schedule sched = (Schedule) it.next();
                    int occupied = this.occupiedSeats(sched, stations.get(1));
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
                Schedule end = scheduleService.findByTrainAndStation(stations.get(1), sch.getTrain(), date);
                anotherOneList.add(end);
            }
            schedulesChain.add(anotherOneList);

            endsOfFirstSegmentSchedule = anotherOneList;
        }

        int ticketChain = schedulesChain.size();
        int min = ENDLESS_WEIGHT;

        for (List<Schedule> sch : schedulesChain) {
            if (sch.size() < min)
                min = sch.size();
        }
        int fullChains = min;

        List<List<Ticket>> tickets = new ArrayList<>();
        for (int i = 0; i < fullChains; i++) {
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

    /**REGISTRATION TICKET IN SYSTEM IN CASE USER CLICK ON "BUY TICKETS"
     *
     * @param ticket
     * @param userName
     */
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
                log.info("TICKET REGISTERED FOR " + user.getLogin() + ": FROM " + beginStation.getName() + " TO " + endStation.getName() + " ON TRAIN " + train.getTrainName() +
                        " ON DATE " + t.getTicketDateDeparture().toString().substring(0, 11));
                t.setUser(user);
                ticketDAO.add(t);
            } else {
                log.info("EXCEPTION: " + ErrorCode.NO_MORE_TICKETS);
                throw new RuntimeBusinessLogicException(ErrorCode.NO_MORE_TICKETS);
            }
        }
    }

    /**HELPER METHOD USED FOR INVALIDATING TICKETS IN CASE OF TRAIN DELETING
     * @see ru.javasch.metro.controller.AdminController#deletingTrain(Long)
     * @return
     */
    @Override
    @Transactional
    public List<Ticket> invalidateNonValidTickets(String trainName) {
        List<Ticket> tickets = ticketDAO.findAllInvalidTickets();
        for (Ticket t : tickets) {
            t.setValid("INVALID");
            String message = "Your ticket on train " + trainName + " was invalidated. REASON: Train was deleted";
            smsSenderService.sendSMS(message, t.getUser().getPhone());
            log.info("TICKET FOR " + t.getUser().getFirstName() + " " + t.getUser().getLastName() + " FROM " + t.getStationBegin().getName() +
                    " TO " + t.getStationEnd().getName() + " ON DATE " + t.getTicketDateDeparture().toString().substring(0, 11) + " WAS INVALIDATE");
        }
        return tickets;
    }

    /**HELPER METHOD FOR SENDING MESSAGES TO USER WHO OWNING INVALID TICKETS
     *
     * @param tickets
     * @throws IOException
     * @throws MessagingException
     */
    @Override
    @Transactional
    public void sendInvalidateMessages(List<Ticket> tickets) throws IOException, MessagingException {
        for (Ticket t : tickets) {
            User user = t.getUser();
            Message message = Message.createInvalidateMessage(user.getLogin());
            mailService.sendMimeMessage(message);
        }
    }

    /**FINDING ALL TICKETS BY USER
     *
     * @return
     */
    @Override
    @Transactional
    public List<Ticket> findAllTicketsByUser() {
        User user = userService.findAuthenticatedUser();
        List<Ticket> tickets = ticketDAO.findAllUserTickets(user);
        return tickets;
    }

    /**FINDING ALL TICKETS BY TRAIN
     *
     * @param train
     * @return
     */
    @Override
    @Transactional
    public List<Ticket> getByTrain(Train train) {
        return ticketDAO.getByTrain(train);
    }

    /**FINDING ALL TICKETS BY DATE.
     * HELPER METHOD FOR
     * @see ScheduledTasks#invalidateTicketForClosedStations()
     * @return
     */
    @Override
    public List<Ticket> getTicketsOnCurrentDate() { return ticketDAO.getTicketsOnCurrentDate(); }
}
