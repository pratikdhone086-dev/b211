package com.app.service;

import java.util.List;

import com.app.entity.Student;

public interface StudentService {

    Student addStudent(Student student);

    Student getStudentById(int id);

    List<Student> getAllStudents();

    Student updateStudent(Student student);

    void deleteStudent(int id);
}