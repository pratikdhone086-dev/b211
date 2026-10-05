package com.app.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Batch {

    @Id
    private int id;

    private String name;
    private String course;
    private String timing;

    public Batch() {
    }

    public Batch(int id, String name, String course, String timing) {
        this.id = id;
        this.name = name;
        this.course = course;
        this.timing = timing;
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

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getTiming() {
        return timing;
    }

    public void setTiming(String timing) {
        this.timing = timing;
    }
}