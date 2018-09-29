package ru.javasch.metro.scheduled;

import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.Transactional;
import ru.javasch.metro.model.Schedule;
import ru.javasch.metro.model.Train;
import ru.javasch.metro.service.interfaces.ScheduleService;

import java.util.List;

@Component
@Log4j
public class ScheduledTask {
    @Autowired
    private ScheduleService scheduleService;

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
}
