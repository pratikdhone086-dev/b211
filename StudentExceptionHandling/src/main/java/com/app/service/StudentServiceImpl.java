package com.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.exceptions.CourseNotFoundException;
import com.app.exceptions.EmailAlreadyExistsException;
import com.app.exceptions.InvalidMobileException;
import com.app.exceptions.InvalidStudentException;
import com.app.exceptions.StudentNotFoundException;
import com.app.model.Student;
import com.app.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository repository;

    @Override
    public Student addStudent(Student student) {

        if (student.getName() == null || student.getName().isEmpty()) {

            throw new InvalidStudentException(
                    "Student name cannot be empty");
        }

        if (student.getEmail() == null || student.getEmail().isEmpty()) {

            throw new InvalidStudentException(
                    "Student email cannot be empty");
        }

        if (repository.existsByEmail(student.getEmail())) {

            throw new EmailAlreadyExistsException(
                    "Email already exists: " + student.getEmail());
        }

        if (student.getMobile() < 1000000000L
                || student.getMobile() > 9999999999L) {

            throw new InvalidMobileException(
                    "Mobile number must be 10 digits");
        }

        if (student.getCourse() == null
                || student.getCourse().isEmpty()) {

            throw new CourseNotFoundException(
                    "Course cannot be empty");
        }

        return repository.save(student);
    }

    @Override
    public List<Student> getAllStudent() {

        return repository.findAll();
    }

    @Override
    public Student getStudentById(int id) {

        return repository.findById(id)
                .orElseThrow(() ->
                        new StudentNotFoundException(
                                "Student not found for id: " + id));
    }

    @Override
    public Student updateStudent(Student student) {

        if (student.getId() == null) {

            throw new StudentNotFoundException(
                    "Student id cannot be null");
        }

        if (!repository.existsById(student.getId())) {

            throw new StudentNotFoundException(
                    "Student not found for id: "
                            + student.getId());
        }

        return repository.save(student);
    }

    @Override
    public String deleteStudent(int id) {

        if (!repository.existsById(id)) {

            throw new StudentNotFoundException(
                    "Student not found for id: " + id);
        }

        repository.deleteById(id);

        return "Student deleted successfully";
    }
}