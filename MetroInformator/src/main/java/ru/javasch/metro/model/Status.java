package ru.javasch.metro.model;

import javax.persistence.*;

@Entity
@Table(name="status")
public class Status {
    private Long id;
    private String statusName;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    @Column(name = "statusName")
    public String getStatusName() { return statusName; }
    public void setStatusName(String statusName) { this.statusName = statusName; }
}
