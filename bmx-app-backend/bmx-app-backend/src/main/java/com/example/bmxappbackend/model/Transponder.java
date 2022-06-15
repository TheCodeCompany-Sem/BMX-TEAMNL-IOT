package com.example.bmxappbackend.model;

import javax.persistence.*;

@Entity
public class Transponder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "datafield_1")
    private int datafield_1;
    @Column(name = "datafield_2")
    private int datafield_2;
    @Column(name = "datafield_3")
    private int datafield_3;
    @Column(name = "datafield_4")
    private int datafield_4;
    @Column(name = "datafield_5")
    private int datafield_5;
    @Column(name = "datafield_6")
    private int datafield_6;
    @Column(name = "datafield_7")
    private int datafield_7;
    @Column(name = "datafield_8")
    private int datafield_8;

    public Transponder(int id, int datafield_1, int datafield_2, int datafield_3, int datafield_4, int datafield_5, int datafield_6, int datafield_7, int datafield_8) {
        this.id = id;
        this.datafield_1 = datafield_1;
        this.datafield_2 = datafield_2;
        this.datafield_3 = datafield_3;
        this.datafield_4 = datafield_4;
        this.datafield_5 = datafield_5;
        this.datafield_6 = datafield_6;
        this.datafield_7 = datafield_7;
        this.datafield_8 = datafield_8;
    }

    public Transponder() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDatafield_1() {
        return datafield_1;
    }

    public void setDatafield_1(int datafield_1) {
        this.datafield_1 = datafield_1;
    }

    public int getDatafield_2() {
        return datafield_2;
    }

    public void setDatafield_2(int datafield_2) {
        this.datafield_2 = datafield_2;
    }

    public int getDatafield_3() {
        return datafield_3;
    }

    public void setDatafield_3(int datafield_3) {
        this.datafield_3 = datafield_3;
    }

    public int getDatafield_4() {
        return datafield_4;
    }

    public void setDatafield_4(int datafield_4) {
        this.datafield_4 = datafield_4;
    }

    public int getDatafield_5() {
        return datafield_5;
    }

    public void setDatafield_5(int datafield_5) {
        this.datafield_5 = datafield_5;
    }

    public int getDatafield_6() {
        return datafield_6;
    }

    public void setDatafield_6(int datafield_6) {
        this.datafield_6 = datafield_6;
    }

    public int getDatafield_7() {
        return datafield_7;
    }

    public void setDatafield_7(int datafield_7) {
        this.datafield_7 = datafield_7;
    }

    public int getDatafield_8() {
        return datafield_8;
    }

    public void setDatafield_8(int datafield_8) {
        this.datafield_8 = datafield_8;
    }
}
