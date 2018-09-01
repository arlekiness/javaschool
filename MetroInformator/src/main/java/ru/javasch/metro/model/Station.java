package ru.javasch.metro.model;

import javax.persistence.Entity;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;


@Entity
@Table(name = "stations")
public class Station {
    private Long id;
    private String name;
    private Set<Branch> branches;
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


    @NotNull
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "station_branch", joinColumns = {
            @JoinColumn(name = "station_id")},
            inverseJoinColumns = {
                    @JoinColumn(name = "branch_id")
            })
    public Set<Branch> getBranches() { return branches; }
    public void setBranches(Set<Branch> branch) { this.branches = branches; }
}
