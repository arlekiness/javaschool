package ru.javasch.metro.DAO.Interfaces;

import org.springframework.stereotype.Repository;
import ru.javasch.metro.model.Schedule;
import ru.javasch.metro.model.Station;

import java.util.Date;
import java.util.List;

@Repository
public interface ScheduleDAO<E extends Schedule> extends GenericDAO<E>  {
    public List<Schedule> getByDate(Date date);
    public List<Schedule> getByStation(Station station);
    public List<Schedule> getByStationAndDate(Schedule schedule);
}
