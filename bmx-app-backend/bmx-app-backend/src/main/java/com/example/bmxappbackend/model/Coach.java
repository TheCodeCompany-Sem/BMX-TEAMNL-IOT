package com.example.bmxappbackend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * @Author: Mortada M'Rabet
 * @Description:
 */
@Entity
public class Coach {
    @Id
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "surname", nullable = false)
    private String surname;

    @OneToMany(mappedBy = "coach")
    private List<Athlete> athletes;

    public Coach(int id, String firstName, String surname, List<Athlete> athletes) {
        this.id = id;
        this.firstName = firstName;
        this.surname = surname;
        this.athletes = athletes;
    }

    public Coach() {
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
