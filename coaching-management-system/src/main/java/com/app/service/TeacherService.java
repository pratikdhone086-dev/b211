package com.app.service;

import java.util.List;

import com.app.entity.Teacher;

public interface TeacherService {

    Teacher addTeacher(Teacher teacher);

    Teacher login(String username, String password);

    List<Teacher> getAllTeachers();

    Teacher getTeacherById(int id);

    Teacher updateTeacher(Teacher teacher);

    void deleteTeacher(int id);
}