package ru.javasch.metro.model;

import javax.persistence.*;

@Entity
@Table(name="roles")
public class Role {
    private Long id;
    private String type;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "type")
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
}
