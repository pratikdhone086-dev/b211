package com.app.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Course {

    @Id
    private int id;

    private String name;
    private double fees;
    private String duration;

    public Course() {
    }

    public Course(int id, String name, double fees, String duration) {
        this.id = id;
        this.name = name;
        this.fees = fees;
        this.duration = duration;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getFees() {
        return fees;
    }

    public void setFees(double fees) {
        this.fees = fees;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}