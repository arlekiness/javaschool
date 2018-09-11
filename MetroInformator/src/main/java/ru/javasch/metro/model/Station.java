package ru.javasch.metro.model;

import javax.validation.constraints.NotNull;

import javax.persistence.Entity;
import javax.persistence.*;
import java.util.Objects;
import java.util.Set;


@Entity
@Table(name = "station")
public class Station {
    private Integer id;
    private String name;
    private Status status;
    private Branch branch;
    private Integer numberOnBranch;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
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

    //============================
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
