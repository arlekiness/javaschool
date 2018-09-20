package ru.javasch.metro.service.interfaces;

import ru.javasch.metro.model.Schedule;
import ru.javasch.metro.model.Station;
import ru.javasch.metro.model.Train;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

public interface ScheduleService {
    public void addSchedule (Schedule schedule);
    public List<Schedule> getAllTrainsOnStation (String stationName, String dateString) throws ParseException;
    public List<Station> addNewSchedules (String trainName, String stationName, String firstDate) throws ParseException;
    public List<Schedule> getAllSchedulesByStationDateAndPath (Station stationBegin, Station stationEnd, Date date, Date now);
    public Schedule findByTrainAndStation (Station station, Train train, Date date);
}
