package ru.javasch.metro.ejb.service;

import lombok.extern.log4j.Log4j;
import ru.javasch.metro.helpers.DataManager;
import ru.javasch.metro.helpers.Listener;
import ru.javasch.metro.helpers.Loader;
import ru.javasch.metro.model.Station;
import ru.javasch.metro.model.TimeSchedule;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeoutException;

@Singleton
@Log4j
public class TimeScheduleBean {
    private DataManager dataManager = DataManager.getInstance();
    private Listener listener = new Listener();
    private Loader loader = Loader.getInstance();


    private List<TimeSchedule> schedules;
    private List<String> stations = dataManager.getStationList();
    private String selectedItem = "Devyatkino";
    private String lastChangesInfo = "No changes ... ";
    private Station selectedItemStation = dataManager.getStations().get(0);

    public List<TimeSchedule> getSchedules() { return schedules; }
    public void setSchedules(List<TimeSchedule> schedules) { this.schedules = schedules; }

    public List<String> getStations() { return stations; }
    public void setStations(List<String> stations) { this.stations = stations; }

    public String getSelectedItem() { return selectedItem; }
    public void setSelectedItem(String selectedItem) {
        this.selectedItem = selectedItem;
        updateSchedules(selectedItem);
        updateSelectedItemStation(selectedItem);
    }

    public Station getSelectedItemStation() { return selectedItemStation; }
    public void setSelectedItemStation(Station selectedItemStation) { this.selectedItemStation = selectedItemStation; }

    public String getLastChangesInfo() { return lastChangesInfo; }
    public void setLastChangesInfo(String lastChangesInfo) { this.lastChangesInfo = lastChangesInfo; }


    public void update() {
        if (dataManager.getStatusChanges() || dataManager.getStationChanges()) {
            lastChangesInfo = dataManager.getHeaderMessage();
            log.info("INVOKED update @schedule ... ");
            /*
            Update schedules for current station if it was updated in real time
             */
            if (dataManager.checkSelectedItem(selectedItem)) {
                updateSchedules(selectedItem);
                updateSelectedItemStation(selectedItem);
            }
            dataManager.resetStatusChanges();
            dataManager.resetStationChanges();
        }
    }


//
    @PostConstruct
    private void init() throws IOException, TimeoutException {
        listener.start();
        updateSchedules(selectedItem);
        updateSelectedItemStation(selectedItem);
    }

    @PreDestroy
    private void destroy() throws IOException, TimeoutException {
        listener.stop();
    }

    private void updateSchedules(String selectedItem) { schedules = dataManager.loadSchedule(selectedItem); }
    private void updateSelectedItemStation(String selectedItem) {
        this.selectedItemStation = dataManager.loadSelectedStation(selectedItem); }
}
