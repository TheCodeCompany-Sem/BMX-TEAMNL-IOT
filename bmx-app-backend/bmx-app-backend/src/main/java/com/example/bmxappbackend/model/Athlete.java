package com.example.bmxappbackend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Athlete {
    @Id
    @Column(name = "id", nullable = false)
    private int id;

    private String name;

    private String surname;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
