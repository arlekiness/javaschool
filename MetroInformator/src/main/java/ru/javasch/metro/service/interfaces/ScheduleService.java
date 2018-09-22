package ru.javasch.metro.service.interfaces;

import ru.javasch.metro.model.Schedule;
import ru.javasch.metro.model.Station;
import ru.javasch.metro.model.Train;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

public interface ScheduleService {
    void addSchedule (Schedule schedule);
    List<Schedule> getAllTrainsOnStation (String stationName, String dateString) throws ParseException;
    List<Station> addNewSchedules (String trainName, String stationName, String firstDate, String firstTime) throws ParseException;
    List<Schedule> getAllSchedulesByStationDateAndPath (Station stationBegin, Station stationEnd, Date date, Date now);
    Schedule findByTrainAndStation (Station station, Train train, Date date);
}
