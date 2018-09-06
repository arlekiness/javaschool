package ru.javasch.metro.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="transition")
public class Transition {
    private Long id;
    private Station station_1;
    private Station station_2;
    private Status status;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    @OneToOne
    public Station getStation_1() { return station_1; }
    public void setStation_1(Station station_1) { this.station_1 = station_1; }

    @OneToOne
    public Station getStation_2() { return station_2; }
    public void setStation_2(Station station_2) { this.station_2 = station_2; }

    @OneToOne
    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transition that = (Transition) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(station_1, that.station_1) &&
                Objects.equals(station_2, that.station_2) &&
                Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, station_1, station_2, status);
    }
}
