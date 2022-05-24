package com.example.bmxappbackend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.aspectj.apache.bcel.generic.TargetLostException;

import javax.persistence.*;
import java.util.List;

/**
 * @Author: Mortada M'Rabet
 * @Description:
 */
@Entity
public class Athlete {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "surname", nullable = false)
    private String surname;

    @ManyToOne(targetEntity = Coach.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "coach_id", insertable = false, updatable = false)
    @JsonBackReference(value = "athletes")
    private Coach coach;

    @Column(name = "coach_id")
    private int coachId;

    @OneToMany(mappedBy = "athlete", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference(value = "trackTimeRecords")
    private List<TrackTimeRecord> trackTimeRecords;

    public Athlete(int id, String firstName, String surname, Coach coach, int coachId, List<TrackTimeRecord> trackTimeRecords) {
        this.id = id;
        this.firstName = firstName;
        this.surname = surname;
        this.coach = coach;
        this.coachId = coachId;
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

    public int getCoachId() {
        return coachId;
    }

    public void setCoachId(int coachId) {
        this.coachId = coachId;
    }
}
