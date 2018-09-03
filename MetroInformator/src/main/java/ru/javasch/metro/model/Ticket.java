package ru.javasch.metro.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="ticket")
public class Ticket {
    private Long id;
    private User user;
    private Train train;
    private Station stationBegin;
    private Station stationEnd;
    private Date ticketDate;
    private Integer price;
    private Branch branch;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    @OneToOne
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    @OneToOne
    public Train getTrain() { return train; }
    public void setTrain(Train train) { this.train = train; }


    @OneToOne
    public Station getStationBegin() { return stationBegin; }
    public void setStationBegin(Station stationBegin) { this.stationBegin = stationBegin; }

    @OneToOne
    public Station getStationEnd() { return stationEnd; }
    public void setStationEnd(Station stationEnd) { this.stationEnd = stationEnd; }

    @Column(name = "ticketDate")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getTicketDate() { return ticketDate; }
    public void setTicketDate(Date ticketDate) { this.ticketDate = ticketDate; }

    @Column(name="price")
    public Integer getPrice() { return price; }
    public void setPrice(Integer price) { this.price = price; }

    @OneToOne
    public Branch getBranch() { return branch; }
    public void setBranch(Branch branch) { this.branch = branch; }


}
