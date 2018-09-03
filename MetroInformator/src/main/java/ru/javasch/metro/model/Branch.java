package ru.javasch.metro.model;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name="branch")
public class Branch {
    private Long id;
    private String branchColor;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    @Column(name="branchColor")
    public String getColor() { return branchColor; }
    public void setColor(String color) { this.branchColor = color; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Branch branch = (Branch) o;
        return Objects.equals(id, branch.id) &&
                Objects.equals(branchColor, branch.branchColor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, branchColor);
    }
}
