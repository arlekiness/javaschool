package ru.javasch.metro.helpers;

import lombok.extern.log4j.Log4j;
import ru.javasch.metro.model.Schedule;
import ru.javasch.metro.model.Station;
import ru.javasch.metro.model.TimeSchedule;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Log4j
public class DataManager {

    private static DataManager dataManager;
    private Loader loader = Loader.getInstance();

    public List<Schedule> schedules = loader.getSchedules();
    public List<Station> stations = loader.getStations();


    private boolean CHANGE_VALUES_FLAG = false;
    private String LAST_CHANGE_MESSAGE = "";
    private String MESSAGE_FOR_HEADER = "No changes...";
    private boolean CHANGE_STATION_FLAG = false;

    public List<Station> getStations() {
        return stations;
    }

    public List<TimeSchedule> loadSchedule(String selectedItem) {
        log.info("INVOKED loadSchedule " + selectedItem);
        return Converter.convertSchedules(selectedItem, schedules);
    }

    public Station loadSelectedStation(String selectedItem) {
        log.info("INVOKED loadSelectedStation " + selectedItem);
//        upStationChanges();
        return Converter.convertSelectedStation(selectedItem, stations);
    }

    public List<String> getStationList() {
        return Converter.convertStationList(stations);
    }

    private void delete(Long id, StringBuilder stateMessage) {
        Schedule schedule = schedules.stream().filter(x -> x.getId().equals(id)).findAny().get();
        if (schedule != null) {
            schedules.remove(schedule);
            stateMessage.append(schedule.getStation() + " ");
        }
    }

    /** DELETE SCHEDULES ON MESSAGE */
    private void deleteSchedules (List<Long> iDs) {
        Schedule schedule = schedules.stream().filter(x -> x.getId().equals(iDs.get(0))).findAny().get();
        MESSAGE_FOR_HEADER = "Train " + schedule.getTrain() + " was removed for some reason";
        StringBuilder stateMessage = new StringBuilder("Schedules on ");
        for (Long id : iDs) {
            delete(id, stateMessage);
        }
        stateMessage.append(" was removed");
        LAST_CHANGE_MESSAGE = stateMessage.toString();
        log.info(LAST_CHANGE_MESSAGE);

    }

    /** GET STATION LIST */
    public Station getStationFromMessage (String stationName) {
        Station needed = null;
        for (Station st : stations) {
            if (st.getName().equals(stationName)) {
                needed = st;
                break;
            }
        }
        return needed;
    }

    /**UPDATE STATION STATUS (OPENING)*/
    public void updateOpenStationStatus(String stationName) {
        Station needed = getStationFromMessage(stationName);
        if (needed != null) {
            needed.setStatus("WORKED");
        }
        try {
            LAST_CHANGE_MESSAGE = stationName + " change status on " + needed.getStatus();
        } catch (NullPointerException ex){
            log.error("updateOpenStationStatus() throw NullPointerException", ex);
            LAST_CHANGE_MESSAGE = "";
        }
        MESSAGE_FOR_HEADER = LAST_CHANGE_MESSAGE;
    }

    /**UPDATE STATION STATUS (CLOSING)*/
    public void updateCloseStationStatus(String stationName) {
        Station needed = getStationFromMessage(stationName);
        if (needed != null) {
            needed.setStatus("CLOSED");
        }
        LAST_CHANGE_MESSAGE = stationName + " change status on " + needed.getStatus();
        MESSAGE_FOR_HEADER = LAST_CHANGE_MESSAGE;
    }

    public List<Long> getSchedulesIDsFromString (String iDs) {
        String[] arr = iDs.split(",");
        for (int i = 0; i < arr.length; i++) {
            String number = arr[i].trim();
            arr[i] = number;
        }
        List<Long> scheduleIDs = new ArrayList<>();
        for (String number : arr) {
            scheduleIDs.add(Long.parseLong(number));
        }
        return scheduleIDs;
    }

    /**DELETING TRAIN ON MESSAGE*/
    public void changeState(String message) throws IOException {
        if (message.contains("deletedtrain")) {
            String iDs = message.substring(24, message.length() - 1);
            if (!iDs.equals("")) {
                List<Long> scheduleIDs = getSchedulesIDsFromString(iDs);
                deleteSchedules(scheduleIDs);
            }
            upStatusChanges();
        } else if (message.contains("deletedupdate")) {
            String iDs = message.substring(25, message.length() - 1);
            if (!iDs.equals("")) {
                List<Long> scheduleIDs = getSchedulesIDsFromString(iDs);
                deleteSchedules(scheduleIDs);
            }
            MESSAGE_FOR_HEADER = "Schedules was updated";
            upStatusChanges();
        } else if (message.contains("stationopen")) {
            String stationName = message.substring(12);
            updateOpenStationStatus(stationName);
            upStationChanges();
        } else if (message.contains("stationclose")) {
            String stationName = message.substring(13);
            updateCloseStationStatus(stationName);
            upStationChanges();
        }
        else  schedules = loader.getSchedules();
    }

    /**GET INSTANCE*/
    public static DataManager getInstance() {
        if (dataManager == null)
            dataManager = new DataManager();
        return dataManager;
    }

    public boolean getStatusChanges() {
        return CHANGE_VALUES_FLAG;
    }

    public  boolean getStationChanges() {
        return CHANGE_STATION_FLAG;
    }

    public void resetStatusChanges() {
        CHANGE_VALUES_FLAG = false;
        LAST_CHANGE_MESSAGE = "";
    }

    public void resetStationChanges() {
        CHANGE_STATION_FLAG = false;
    }

    public void upStatusChanges() {
        CHANGE_VALUES_FLAG = true;
    }

    public void upStationChanges() {
        CHANGE_STATION_FLAG = true;
    }

    public String getLastInfoChanges() {
        return LAST_CHANGE_MESSAGE;
    }

    public String getHeaderMessage() {
        return MESSAGE_FOR_HEADER;
    }

    public boolean checkSelectedItem(String selectedItem) {
        return LAST_CHANGE_MESSAGE.contains(selectedItem);
    }
}
