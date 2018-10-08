package ru.javasch.metro.helpers;

import ru.javasch.metro.model.Schedule;
import ru.javasch.metro.model.Station;
import ru.javasch.metro.model.TimeSchedule;
import ru.javasch.metro.utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Converter {
    /**
     * @param stationMain
     * @param schedules
     * @return
     */
    public static List<TimeSchedule> convertSchedules(String stationMain, List<Schedule> schedules) {
        return schedules.stream()
                .filter(schedule -> schedule.getStation().equals(stationMain) && Utils.checkScheduleForToday(schedule))
                .map(x -> {
                    TimeSchedule timeSchedule = new TimeSchedule();
                    timeSchedule.setStation(x.getStation());
                    timeSchedule.setEndPointStation(x.getEndPointStation());
                    timeSchedule.setTrain(x.getTrain());
                    timeSchedule.setArrivalTime(Utils.parseToTime(x.getDateArrival()));
                    timeSchedule.setDepartureTime(Utils.parseToTime(x.getDateDeparture()));
                    return timeSchedule;
                }).collect(Collectors.toList());
    }

    public static List<String> convertStationList(List<Station> stations) {
        List<String> stationList = new ArrayList<>();
        for (Station st : stations) {
            stationList.add(st.getName());
        }
        return stationList;
    }

    public static Station convertSelectedStation(String neededStation, List<Station> stations) {
        Station needed = null;
        for (Station st : stations) {
            if (st.getName().equals(neededStation)) {
                needed = st;
                break;
            }
        }
        return needed;
    }
}
