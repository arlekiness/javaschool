package ru.javasch.metro.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="trains")
public class Train {
    private Long id;
    private String trainName;
    private Set<Seat> seats;
    private Status status;


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "trainName")
    public String getTrainName() {
        return trainName;
    }
    public void setTrainName(String trainName) {
        this.trainName = trainName;
    }

    @OneToMany(mappedBy = "train", cascade = CascadeType.ALL)
    public Set<Seat> getSeats() {
        return seats;
    }
    public void setSeats(Set<Seat> seats) {
        this.seats = seats;
    }

    @OneToOne
    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }

    @Override
    public String toString() {
        return "Train{" +
                "trainName='" + trainName + '\'' +
                '}';
    }
}
