package com.app.service;

import java.util.List;

import com.app.entity.Course;

public interface CourseService {

    Course addCourse(Course course);

    List<Course> getAllCourses();

    Course getCourseById(int id);

    Course updateCourse(Course course);

    void deleteCourse(int id);
}