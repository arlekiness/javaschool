package ru.javasch.metro.service.Implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javasch.metro.DAO.Interfaces.StationDAO;
import ru.javasch.metro.DAO.Interfaces.TicketDAO;
import ru.javasch.metro.DAO.Interfaces.TrainDAO;
import ru.javasch.metro.DAO.Interfaces.UserDAO;
import ru.javasch.metro.configuration.constants.Utils;
import ru.javasch.metro.model.*;
import ru.javasch.metro.service.Interfaces.ScheduleService;
import ru.javasch.metro.service.Interfaces.StationService;
import ru.javasch.metro.service.Interfaces.TicketService;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


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

    @Autowired
    ScheduleService scheduleService;


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
        System.out.println(schedulesChain.size());
        for (List<Schedule> sc : schedulesChain)
            System.out.print(sc.size() + " ");

        System.out.println("********" + schedulesChain.get(0).get(0) + "********>********" + schedulesChain.get(1).get(0));
//        System.out.println("===========================");
//        System.out.println("********" + schedulesChain.get(0).get(1) + "********>********" + schedulesChain.get(1).get(1)
//                            + "********>********" + schedulesChain.get(2).get(1) + "********>********" + schedulesChain.get(3).get(1) + "********");
//        System.out.println("===========================");
//        System.out.println("********" + schedulesChain.get(0).get(2) + "********>********" + schedulesChain.get(1).get(2)
//                + "********>********" + schedulesChain.get(2).get(2) + "********>********" + schedulesChain.get(3).get(2) + "********");
//        System.out.println("===========================");
//        System.out.println("********" + schedulesChain.get(0).get(3) + "********>********" + schedulesChain.get(1).get(3)
//                + "********>********" + schedulesChain.get(2).get(3) + "********>********" + schedulesChain.get(3).get(3) + "********");
//        System.out.println("===========================");
        return new ArrayList<List<Ticket>>();
    }
}
