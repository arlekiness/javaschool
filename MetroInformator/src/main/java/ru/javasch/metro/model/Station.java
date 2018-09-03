package ru.javasch.metro.model;

import javax.persistence.Entity;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.Set;


@Entity
@Table(name = "station")
public class Station {
    private Long id;
    private String name;
    private Status status;
    private Branch branch;
    private Integer numberOnBranch;
    private Set<Station> transitions;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }


    @OneToOne
    @NotNull
    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }

    @OneToOne
    public Branch getBranch() { return branch; }
    public void setBranch(Branch branch) { this.branch = branch; }

    @Column(name = "numberOnBranch")
    public Integer getNumberOnBranch() { return numberOnBranch; }
    public void setNumberOnBranch(Integer numberOnBranch) { this.numberOnBranch = numberOnBranch; }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "transition_station", joinColumns = {
            @JoinColumn(name = "station_id_from")},
            inverseJoinColumns = {
                    @JoinColumn(name = "station_id_to")
            })
    public Set<Station> getTransitions() { return transitions; }
    public void setTransitions(Set<Station> transitions) { this.transitions = transitions; }

    @Override
    public String toString() {
        return "Station{" +
                "name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Station station = (Station) o;
        return Objects.equals(id, station.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
