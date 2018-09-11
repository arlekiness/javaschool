package ru.javasch.metro.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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
    private Date ticketDateDeparture;
    private Date ticketDateArrival;
    private Integer price;
    private Branch branch;
    private Long valid;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    @OneToOne
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    @ManyToOne
    @JoinColumn(name = "train_id", nullable = false)
    public Train getTrain() { return train; }
    public void setTrain(Train train) { this.train = train; }

//(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @OneToOne
    public Station getStationBegin() { return stationBegin; }
    public void setStationBegin(Station stationBegin) { this.stationBegin = stationBegin; }

    @OneToOne
    public Station getStationEnd() { return stationEnd; }
    public void setStationEnd(Station stationEnd) { this.stationEnd = stationEnd; }

    @Column(name = "ticketDateDeparture")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getTicketDateDeparture() { return ticketDateDeparture; }
    public void setTicketDateDeparture(Date ticketDateDeparture) { this.ticketDateDeparture = ticketDateDeparture; }

    @Column(name = "ticketDateArrival")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getTicketDateArrival() { return ticketDateArrival; }
    public void setTicketDateArrival(Date ticketDateArrival) { this.ticketDateArrival = ticketDateArrival; }

    @Column(name="price")
    public Integer getPrice() { return price; }
    public void setPrice(Integer price) { this.price = price; }

    @OneToOne
    public Branch getBranch() { return branch; }
    public void setBranch(Branch branch) { this.branch = branch; }

    public Long getValid() { return valid; }
    public void setValid(Long valid) { this.valid = valid; }
}
