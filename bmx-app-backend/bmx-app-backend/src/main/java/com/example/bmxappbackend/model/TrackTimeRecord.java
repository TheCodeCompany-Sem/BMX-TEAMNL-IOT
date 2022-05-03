package com.example.bmxappbackend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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

    @Column(name ="RecordedTime", nullable = false)
    private Date RecordedTime;

    @Column(name ="RecordedValue", nullable = false)
    private double RecordedValue;

    public TrackTimeRecord(int id, Date recordedTime, double recordedValue) {
        this.id = id;
        RecordedTime = recordedTime;
        RecordedValue = recordedValue;
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
        return RecordedTime;
    }

    public void setRecordedTime(Date recordedTime) {
        RecordedTime = recordedTime;
    }

    public double getRecordedValue() {
        return RecordedValue;
    }

    public void setRecordedValue(double recordedValue) {
        RecordedValue = recordedValue;
    }
}
