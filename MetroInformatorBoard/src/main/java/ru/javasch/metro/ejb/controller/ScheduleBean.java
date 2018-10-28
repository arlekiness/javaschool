package ru.javasch.metro.ejb.controller;

import lombok.extern.log4j.Log4j;
import ru.javasch.metro.ejb.service.TimeScheduleBean;
import ru.javasch.metro.model.TimeSchedule;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.List;

@ViewScoped
@ManagedBean
@Log4j
public class ScheduleBean {

    @EJB
    private TimeScheduleBean timeScheduleBean;

    public List<TimeSchedule> getSchedule() {
        return timeScheduleBean.getSchedules();
    }

    public void update() {
        timeScheduleBean.update();
    }

    public String getLastChangedInfo() {
        return timeScheduleBean.getLastChangesInfo();
    }

    public List<String> getStations() {
        return timeScheduleBean.getStations();
    }

    public String getSelectedItem() {
        return timeScheduleBean.getSelectedItem();
    }
    public void setSelectedItem(String selectedItem) {
        timeScheduleBean.setSelectedItem(selectedItem);
    }

    public String getSelectedStation() { return timeScheduleBean.getSelectedItemStation().getName();}
    public String getSelectedStationStatus() { return timeScheduleBean.getSelectedItemStation().getStatus();}
}
