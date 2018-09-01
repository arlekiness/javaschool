package ru.javasch.metro.DTO;

import ru.javasch.metro.model.Station;
import ru.javasch.metro.model.Train;

import java.util.Date;

public class ScheduleDTO {
    private String dateDeparture;
    private String dateArrival;
    private String trainName;
    private String station;
    private String endPointStationName;

    public String getDateDeparture() { return dateDeparture; }
    public void setDateDeparture(String dateDeparture) { this.dateDeparture = dateDeparture; }

    public String getDateArrival() { return dateArrival; }
    public void setDateArrival(String dateArrival) { this.dateArrival = dateArrival; }

    public String getTrainName() { return trainName; }
    public void setTrainName(String trainName) { this.trainName = trainName; }

    public String getStation() { return station; }
    public void setStation(String station) { this.station = station; }

    public String getEndPointStationName() { return endPointStationName; }
    public void setEndPointStationName(String endPointStationName) { this.endPointStationName = endPointStationName; }
}
