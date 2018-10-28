package ru.javasch.metro.dto;

import java.io.Serializable;
import java.util.Objects;

public class ScheduleDTO implements Serializable {
    Long id;
    String dateDeparture;
    String dateArrival;
    String train;
    String station;
    String endPointStation;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ScheduleDTO that = (ScheduleDTO) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(dateDeparture, that.dateDeparture) &&
                Objects.equals(dateArrival, that.dateArrival) &&
                Objects.equals(train, that.train) &&
                Objects.equals(station, that.station) &&
                Objects.equals(endPointStation, that.endPointStation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dateDeparture, dateArrival, train, station, endPointStation);
    }
}
