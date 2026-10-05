
package com.app.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.entity.Course;
import com.app.service.CourseService;

@RestController
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping
    public Course addCourse(@RequestBody Course course) {
        return courseService.addCourse(course);
    }

    @GetMapping
    public List<Course> getAllCourses() {
        return courseService.getAllCourses();
    }

    @GetMapping("/{id}")
    public Course getCourseById(@PathVariable int id) {

        Course course = courseService.getCourseById(id);

        if (course == null) {
            throw new RuntimeException(
                    "Course with ID " + id + " not found"
            );
        }

        return course;
    }

    @PutMapping
    public Course updateCourse(@RequestBody Course course) {

        Course existingCourse =
                courseService.getCourseById(course.getId());

        if (existingCourse == null) {
            throw new RuntimeException(
                    "Course with ID "
                    + course.getId()
                    + " not found"
            );
        }

        return courseService.updateCourse(course);
    }

    @DeleteMapping("/{id}")
    public String deleteCourse(@PathVariable int id) {

        Course course = courseService.getCourseById(id);

        if (course == null) {
            throw new RuntimeException(
                    "Course with ID " + id + " not found"
            );
        }

        courseService.deleteCourse(id);

        return "Course deleted successfully";
    }
}

