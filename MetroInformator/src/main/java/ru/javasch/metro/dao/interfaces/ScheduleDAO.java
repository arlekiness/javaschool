package ru.javasch.metro.dao.interfaces;

import org.springframework.scheduling.annotation.Schedules;
import ru.javasch.metro.model.Schedule;
import ru.javasch.metro.model.Station;
import ru.javasch.metro.model.Train;

import java.util.Date;
import java.util.List;

public interface ScheduleDAO<E extends Schedule> extends GenericDAO<E> {
    List<Schedule> getByStationAndDate(Station station, Date date);

    List getByStationsAndDate(Station stationBegin, Station endPointStation, Date date, Date now);

    Schedule findByTrainAndStation(Train train, Station station, Date date);

    List<Schedule> getPastSchedules();
}
