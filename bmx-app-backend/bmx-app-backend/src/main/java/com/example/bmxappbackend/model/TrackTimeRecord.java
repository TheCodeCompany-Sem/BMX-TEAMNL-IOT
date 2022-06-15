package com.example.bmxappbackend.model;

import com.example.bmxappbackend.views.TrackTimeRecordView;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonView;

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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @JsonView(TrackTimeRecordView.base.class)
    @Column(name ="recorded_time")
    private Date recordedTime;

    @JsonView(TrackTimeRecordView.base.class)
    @Column(name ="humidity", nullable = false)
    private double humidity;

    @JsonView(TrackTimeRecordView.base.class)
    @Column(name ="temperature", nullable = false)
    private double temperature;

    @JsonView(TrackTimeRecordView.base.class)
    @Column(name ="wind_direction", nullable = false)
    private String windDirection;

    @JsonView(TrackTimeRecordView.base.class)
    @Column(name ="wind_speed", nullable = false)
    private double windSpeed;

    @JsonView(TrackTimeRecordView.base.class)
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "athlete_id")
    @JsonBackReference(value = "trackTimeRecords")
    private Athlete athlete;


    public TrackTimeRecord(int id, Date recordedTime, double humidity, double temperature, String windDirection, double windSpeed, Athlete athlete) {
        this.id = id;
        this.recordedTime = recordedTime;
        this.humidity = humidity;
        this.temperature = temperature;
        this.windDirection = windDirection;
        this.windSpeed = windSpeed;
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

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double recordedValue) {
        this.humidity = recordedValue;
    }

    public double getWindSpeed() {
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

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public String getWindDirection() {
        return windDirection;
    }

    public void setWindDirection(String windDirection) {
        this.windDirection = windDirection;
    }
}

