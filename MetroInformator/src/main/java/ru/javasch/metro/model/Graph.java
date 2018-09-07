package ru.javasch.metro.model;

import javax.persistence.*;

@Entity
@Table(name="graph")
public class Graph {
    private Long id;
    private Station stationFrom;
    private Station stationTo;
    private Integer weight;
    private Integer oldWeight;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    @OneToOne
    public Station getStationFrom() { return stationFrom; }
    public void setStationFrom(Station stationFrom) { this.stationFrom = stationFrom; }

    @OneToOne
    public Station getStationTo() { return stationTo; }
    public void setStationTo(Station stationTo) { this.stationTo = stationTo; }

    @Column(name="weight")
    public Integer getWeight() { return weight; }
    public void setWeight(Integer weight) { this.weight = weight; }

    @Column(name="oldWeight")
    public Integer getOldWeight() { return oldWeight; }
    public void setOldWeight(Integer oldWeight) { this.oldWeight = oldWeight; }
}
