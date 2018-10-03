package ru.javasch.metro.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "schedule")
public class Schedule {
    private Long id;
    private Date dateDeparture;
    private Date dateArrival;
    private Train train;
    private Station station;
    private Station endPointStation;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "date_arrival")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getDateArrival() {
        return dateArrival;
    }

    public void setDateArrival(Date dateArrival) {
        this.dateArrival = dateArrival;
    }

    @Column(name = "date_departure")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getDateDeparture() {
        return dateDeparture;
    }
    public void setDateDeparture(Date dateDeparture) {
        this.dateDeparture = dateDeparture;
    }

    @OneToOne
    public Train getTrain() {
        return train;
    }
    public void setTrain(Train train) {
        this.train = train;
    }

    @OneToOne
    public Station getStation() {
        return station;
    }
    public void setStation(Station station) {
        this.station = station;
    }

    @OneToOne
    public Station getEndPointStation() {
        return endPointStation;
    }
    public void setEndPointStation(Station endPointStation) {
        this.endPointStation = endPointStation;
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "dateDeparture=" + dateDeparture +
                ", dateArrival=" + dateArrival +
                ", train=" + train +
                ", station=" + station +
                ", endPointStation=" + endPointStation +
                '}';
    }
}
