package ru.javasch.metro.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="train")
public class Train {
    private Long id;
    private String trainName;
    private Integer capacity;
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

    @OneToOne
    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }

    @Column(name = "capacity")
    public Integer getCapacity() { return capacity; }
    public void setCapacity(Integer capacity) { this.capacity = capacity; }

    @Override
    public String toString() {
        return "Train{" +
                "trainName='" + trainName + '\'' +
                '}';
    }
}
