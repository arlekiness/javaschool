package ru.javasch.metro.DAO.Interfaces;

import org.springframework.stereotype.Repository;
import ru.javasch.metro.model.Schedule;
import ru.javasch.metro.model.Station;
import ru.javasch.metro.model.Train;

import java.util.Date;
import java.util.List;

@Repository
public interface ScheduleDAO<E extends Schedule> extends GenericDAO<E>  {
    public List<Schedule> getByStationAndDate(Station station, Date date);
    public List getByStationsAndDate(Station stationBegin, Station endPointStation, Date date, Date now);
    public Schedule findByTrainAndStation (Train train, Station station);

}
