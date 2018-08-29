package ru.javasch.metro.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name="users")
public class User {
    private Long id;
    private String firstName;
    private String lastName;
    private String login;
    private String password;
    private String birthDay;
    private String sex;
    private Set<Role> roles;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "firstName")
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @NotNull
    @Column(name = "lastName")
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = "login")
    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }

    @Column(name = "password")
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "birthDay")
    public String getBirthDay() {
        return birthDay;
    }
    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    @Column(name = "sex")
    public String getSex() {
        return sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }

    @NotNull
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles", joinColumns = {
            @JoinColumn(name = "user_id")},
            inverseJoinColumns = {
                    @JoinColumn(name = "role_id")
            })
    public Set<Role> getRoles() {
        return roles;
    }
    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

}
