package ru.javasch.metro.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="branches")
public class Branch {
    private long id;
    private String branchColor;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    @Column(name="branchColor")
    public String getColor() { return branchColor; }
    public void setColor(String color) { this.branchColor = color; }
}
