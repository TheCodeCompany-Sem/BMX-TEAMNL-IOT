package com.example.bmxappbackend.model;

import javax.persistence.*;
import java.util.Date;

/**
 * @Author Mortada M'Rabet
 * Entity for saving records of the athletes performance
 * //Concept so changes can be made
 */
@Entity
public class TrackTimeRecord {
    @Id
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name ="recorded_time", nullable = false)
    private Date recordedTime;


    @Column(name ="recorded_value", nullable = false)
    private double recordedValue;

    @Column(name ="wind_speed", nullable = false)
    private int windSpeed;

    @ManyToOne
    @JoinColumn(name = "athlete_id", nullable = false)
    private Athlete athlete;

    public TrackTimeRecord(int id, Date recordedTime, double recordedValue, int windSpeed, Athlete athlete) {
        this.id = id;
        this.recordedTime = recordedTime;
        this.recordedValue = recordedValue;
        this.windSpeed = windSpeed;
        this.athlete = athlete;
    }

    public TrackTimeRecord() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getRecordedTime() {
        return recordedTime;
    }

    public void setRecordedTime(Date recordedTime) {
        this.recordedTime = recordedTime;
    }

    public double getRecordedValue() {
        return recordedValue;
    }

    public void setRecordedValue(double recordedValue) {
        this.recordedValue = recordedValue;
    }

    public int getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(int windSpeed) {
        this.windSpeed = windSpeed;
    }

    public Athlete getAthlete() {
        return athlete;
    }

    public void setAthlete(Athlete athlete) {
        this.athlete = athlete;
    }
}
