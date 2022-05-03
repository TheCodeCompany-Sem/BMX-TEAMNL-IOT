package com.example.bmxappbackend.model;

import javax.persistence.*;
import java.util.List;

/**
 * @Author: Mortada M'Rabet
 * @Description:
 */
@Entity
public class Athlete {
    @Id
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "surname", nullable = false)
    private String surname;

    @ManyToOne
    @JoinColumn(name = "coach_id", nullable = false)
    private Coach coach;

    @OneToMany(mappedBy = "athlete")
    private List<TrackTimeRecord> trackTimeRecords;

    public Athlete(int id, String firstName, String surname, Coach coach, List<TrackTimeRecord> trackTimeRecords) {
        this.id = id;
        this.firstName = firstName;
        this.surname = surname;
        this.coach = coach;
        this.trackTimeRecords = trackTimeRecords;
    }

    public Athlete() {
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
