package ru.javasch.metro.scheduled;

import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.Transactional;
import ru.javasch.metro.model.*;
import ru.javasch.metro.service.interfaces.ScheduleService;
import ru.javasch.metro.service.interfaces.TicketService;
import ru.javasch.metro.utils.Utils;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Component
@Log4j
public class ScheduledTasks {
    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private TicketService ticketService;

    @Scheduled(initialDelay = 10000, fixedDelay = 180000)
    @Transactional
    public void deletePastSchedules() {
        List<Schedule> schedules = scheduleService.getPastSchedules();
        int count = schedules.size();
        log.info("TIME SCHEDULE CLEANER STARTED");
        for (Schedule sch : schedules) {
            Train train = sch.getTrain();
            train.deleteSchedule(sch);
        }
        log.info("TIME SCHEDULE CLEANER END WORK. CLEANED " + count + " RECORDS");
    }

    @Scheduled(initialDelay = 0, fixedDelay = 60000)
    @Transactional
    public void invalidateTicketForClosedStations () {
        log.info("TICKET INVALIDATOR CLEANER STARTED");
        Date now = new Date();
        List<Ticket> tickets = ticketService.getTicketsOnCurrentDate();
        for (Ticket t : tickets) {
            Status begin = t.getStationBegin().getStatus();
            Status end = t.getStationEnd().getStatus();
            Date ticketDeparture = t.getTicketDateDeparture();
            Date ticketArrival = t.getTicketDateArrival();
            if (Utils.twoDateSubstraction(now, ticketDeparture) > 0 && Utils.twoDateSubstraction(now, ticketDeparture) < 10
                                                                    && begin.getStatusName().equals("CLOSED")) {
                t.setValid("INVALID");
            }
            if (Utils.twoDateSubstraction(now, ticketArrival) > 0 && Utils.twoDateSubstraction(now, ticketArrival) < 10
                    && begin.getStatusName().equals("CLOSED")) {
                t.setValid("INVALID");
            }
        }
    }
}
