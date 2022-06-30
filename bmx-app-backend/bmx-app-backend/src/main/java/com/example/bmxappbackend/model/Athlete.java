package com.example.bmxappbackend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.aspectj.apache.bcel.generic.TargetLostException;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @JsonIgnore
    @ManyToOne(targetEntity = Coach.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "coach_id")
    @JsonBackReference(value = "athletes")
    private Coach coach;

    @OneToMany(mappedBy = "athlete")
    @JsonManagedReference(value = "trackTimeRecords")
    private Set<TrackTimeRecord> trackTimeRecords = new HashSet<>();

    public Athlete(int id, String firstName, String surname, Coach coach, Set<TrackTimeRecord> trackTimeRecords) {
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

    public Set<TrackTimeRecord> getTrackTimeRecords() {
        return trackTimeRecords;
    }

    public void setTrackTimeRecords(Set<TrackTimeRecord> trackTimeRecords) {
        this.trackTimeRecords = trackTimeRecords;
    }
}
