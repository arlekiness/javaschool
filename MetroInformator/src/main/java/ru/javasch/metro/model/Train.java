package ru.javasch.metro.model;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "train")
public class Train {
    private Long id;
    private String trainName;
    private Integer capacity;
    private Status status;
    private Set<Schedule> schedule;


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
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Column(name = "capacity")
    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    @OneToMany(mappedBy = "train", cascade = CascadeType.ALL, orphanRemoval = true)
    public Set<Schedule> getSchedule() {
        return schedule;
    }
    public void addSchedule(Schedule sch) { schedule.add(sch); }
    public void deleteSchedule(Schedule sch) {
        schedule.remove(sch);
        sch.setTrain(null);
    }
    public void setSchedule(Set<Schedule> schedule) {
        this.schedule = schedule;
    }

    @Override
    public String toString() {
        return "Train{" +
                "trainName='" + trainName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Train train = (Train) o;
        return Objects.equals(id, train.id) &&
                Objects.equals(trainName, train.trainName) &&
                Objects.equals(capacity, train.capacity) &&
                Objects.equals(status, train.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, trainName, capacity, status);
    }
}
