package ru.javasch.metro.helpers;

import lombok.extern.log4j.Log4j;
import ru.javasch.metro.model.Schedule;
import ru.javasch.metro.model.TimeSchedule;

import java.io.IOException;
import java.util.List;

@Log4j
public class DataManager {

    private static DataManager dataManager;
    private Loader loader = Loader.getInstance();

    public List<Schedule> schedules = loader.getSchedules();

    private boolean CHANGE_VALUES_FLAG = false;

    private String LAST_CHANGE_MESSAGE = "";


    public List<TimeSchedule> loadScheduleDeparture(String selectedItem) {
        return Converter.conventDeparture(selectedItem, schedules);
    }

    public List<TimeSchedule> loadScheduleArrival(String selectedItem) {
        return Converter.convertArrival(selectedItem, schedules);
    }

//    private void add(Long id) throws IOException {
//        Schedule schedule = loader.getById(id);
//        if (schedule != null) {
//            schedules.add(schedule);
//            LAST_CHANGE_MESSAGE = "Create new schedule between " + schedule.getStationDepartureName() + " - " + schedule.getStationArrivalName();
//        }
//        log.info(LAST_CHANGE_MESSAGE);
//    }
//
//    private void update(Long id) throws IOException {
//        Schedule newSchedule = loader.getById(id);
//        Schedule oldSchedule = schedules.stream().filter(x -> x.getId().equals(id)).findAny().get();
//        if (newSchedule != null) {
//            schedules.remove(oldSchedule);
//            schedules.add(newSchedule);
//            LAST_CHANGE_MESSAGE = "Schedule between " + newSchedule.getStationDepartureName() + " - " + newSchedule.getStationArrivalName() + " was updated";
//        }
//        log.info(LAST_CHANGE_MESSAGE);
//    }

//    private void delete(Long id) {
//        Schedule schedule = schedules.stream().filter(x -> x.getId().equals(id)).findAny().get();
//        if (schedule != null) {
//            schedules.remove(schedule);
//            LAST_CHANGE_MESSAGE = "Schedule between " + schedule.getStationDepartureName() + " - " + schedule.getStationArrivalName() + " was deleted";
//        }
//        log.info(LAST_CHANGE_MESSAGE);
//    }

//    public void changeState(String message) throws IOException {
//        final Long id = Long.valueOf(message.substring(message.indexOf("id=")).replace("id=", ""));
//        if (message.contains("create"))
//            add(id);
//        else if (message.contains("update"))
//            update(id);
//        else delete(id);
//        upStatusChanges();
//    }

    public static DataManager getInstance() {
        if (dataManager == null)
            dataManager = new DataManager();
        return dataManager;
    }

    public boolean getStatusChanges() {
        return CHANGE_VALUES_FLAG;
    }

    public void resetStatusChanges() {
        CHANGE_VALUES_FLAG = false;
        LAST_CHANGE_MESSAGE = "";
    }

    public void upStatusChanges() {
        CHANGE_VALUES_FLAG = true;
    }

    public String getLastInfoChanges() {
        return LAST_CHANGE_MESSAGE;
    }

    public boolean checkSelectedItem(String selectedItem) {
        return LAST_CHANGE_MESSAGE.contains(selectedItem);
    }
}
