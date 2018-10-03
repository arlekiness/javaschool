package ru.javasch.metro.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "lastdate")
public class LastDateSchedule {
    private Long id;
    private Date dateSchedule;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    @Column(name = "date_need")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getDateSchedule() { return dateSchedule; }
    public void setDateSchedule(Date dateSchedule) { this.dateSchedule = dateSchedule; }
}
