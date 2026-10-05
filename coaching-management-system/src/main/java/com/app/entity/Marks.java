package com.app.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Marks {

    @Id
    private int id;

    private int studentId;
    private String subject;
    private double marks;

    public Marks() {
    }

    public Marks(int id, int studentId, String subject, double marks) {
        this.id = id;
        this.studentId = studentId;
        this.subject = subject;
        this.marks = marks;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public double getMarks() {
        return marks;
    }

    public void setMarks(double marks) {
        this.marks = marks;
    }
}