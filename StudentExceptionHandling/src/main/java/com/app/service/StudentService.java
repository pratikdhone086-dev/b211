package com.app.service;

import java.util.List;

import com.app.model.Student;

public interface StudentService {

    Student addStudent(Student student);

    List<Student> getAllStudent();

    Student getStudentById(int id);

    Student updateStudent(Student student);

    String deleteStudent(int id);
}