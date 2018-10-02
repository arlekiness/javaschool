package ru.javasch.metro.model;

import java.io.Serializable;
import java.util.Objects;

public class Schedule implements Serializable {
    private Long id;
    private String dateDeparture;
    private String dateArrival;
    private String train;
    private String station;
    private String endPointStation;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getDateDeparture() { return dateDeparture; }
    public void setDateDeparture(String dateDeparture) { this.dateDeparture = dateDeparture; }

    public String getDateArrival() { return dateArrival; }
    public void setDateArrival(String dateArrival) { this.dateArrival = dateArrival; }

    public String getTrain() { return train; }
    public void setTrain(String train) { this.train = train; }

    public String getStation() { return station; }
    public void setStation(String station) { this.station = station; }

    public String getEndPointStation() { return endPointStation; }
    public void setEndPointStation(String endPointStation) { this.endPointStation = endPointStation; }
}
