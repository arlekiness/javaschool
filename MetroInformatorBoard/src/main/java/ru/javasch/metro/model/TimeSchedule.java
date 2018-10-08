package ru.javasch.metro.model;

public class TimeSchedule {
    private String arrivalTime;
    private String departureTime;
    private String station;
    private String train;
    private String endPointStation;

    public String getArrivalTime() { return arrivalTime; }
    public void setArrivalTime(String arrivalTime) { this.arrivalTime = arrivalTime; }

    public String getDepartureTime() { return departureTime; }
    public void setDepartureTime(String departureTime) { this.departureTime = departureTime; }

    public String getStation() { return station; }
    public void setStation(String station) { this.station = station; }

    public String getTrain() { return train; }
    public void setTrain(String train) { this.train = train; }

    public String getEndPointStation() { return endPointStation; }
    public void setEndPointStation(String endPointStation) { this.endPointStation = endPointStation; }
}
