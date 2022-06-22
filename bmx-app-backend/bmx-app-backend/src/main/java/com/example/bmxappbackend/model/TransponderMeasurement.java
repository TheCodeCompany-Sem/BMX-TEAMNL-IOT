package com.example.bmxappbackend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
public class TransponderMeasurement {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "transponderCode")
    private String transponderCode;

    @Column(name = "time")
    private LocalTime time;

    @Column(name = "lap")
    private int lap;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "athlete_id")
    @JsonBackReference(value = "transponderMeasurements")
    private Athlete athlete;

    public TransponderMeasurement(int id, String transponderCode, LocalTime time, int lap, Athlete athlete) {
        this.id = id;
        this.transponderCode = transponderCode;
        this.time = time;
        this.lap = lap;
        this.athlete = athlete;
    }

    public TransponderMeasurement() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTransponderCode() {
        return transponderCode;
    }

    public void setTransponderCode(String transponderCode) {
        this.transponderCode = transponderCode;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public int getLap() {
        return lap;
    }

    public void setLap(int lap) {
        this.lap = lap;
    }

    public Athlete getAthlete() {
        return athlete;
    }

    public void setAthlete(Athlete athlete) {
        this.athlete = athlete;
    }
}
