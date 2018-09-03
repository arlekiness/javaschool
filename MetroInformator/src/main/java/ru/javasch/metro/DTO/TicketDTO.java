package ru.javasch.metro.DTO;

import ru.javasch.metro.model.Station;

public class TicketDTO {
    private String dateDeparture;
    private String stationBegin;
    private String stationEnd;
    private String trainName;
    private String userId;
    private String Price;
    private String endPointStation;

    public String getDateDeparture() { return dateDeparture; }
    public void setDateDeparture(String dateDeparture) { this.dateDeparture = dateDeparture; }

    public String getStationBegin() { return stationBegin; }
    public void setStationBegin(String stationBegin) { this.stationBegin = stationBegin; }

    public String getStationEnd() { return stationEnd; }
    public void setStationEnd(String stationEnd) { this.stationEnd = stationEnd; }

    public String getPrice() { return Price; }
    public void setPrice(String price) { Price = price; }

    public String getEndPointStation() { return endPointStation; }
    public void setEndPointStation(String endPointStation) { this.endPointStation = endPointStation; }

    public String getTrainName() { return trainName; }
    public void setTrainName(String trainName) { this.trainName = trainName; }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    @Override
    public String toString() {
        return "TicketDTO{" +
                "dateDeparture='" + dateDeparture + '\'' +
                ", stationBegin='" + stationBegin + '\'' +
                ", stationEnd='" + stationEnd + '\'' +
                ", trainName='" + trainName + '\'' +
                ", userId='" + userId + '\'' +
                ", Price='" + Price + '\'' +
                ", endPointStation='" + endPointStation + '\'' +
                '}';
    }
}
