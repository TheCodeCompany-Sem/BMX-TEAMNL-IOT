package com.example.bmxappbackend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

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
    @JsonBackReference
    private Coach coach;

    @OneToMany(mappedBy = "athlete", cascade = CascadeType.PERSIST)
    @JsonManagedReference
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

    public Coach getCoach() {
        return coach;
    }

    public void setCoach(Coach coach) {
        this.coach = coach;
    }

    public List<TrackTimeRecord> getTrackTimeRecords() {
        return trackTimeRecords;
    }

    public void setTrackTimeRecords(List<TrackTimeRecord> trackTimeRecords) {
        this.trackTimeRecords = trackTimeRecords;
    }
}
