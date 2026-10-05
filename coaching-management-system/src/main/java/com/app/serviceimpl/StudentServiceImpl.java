package com.app.serviceimpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.app.entity.Student;
import com.app.repository.StudentRepository;
import com.app.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Student getStudentById(int id) {
        return studentRepository.findById(id).orElse(null);
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student updateStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public void deleteStudent(int id) {
        studentRepository.deleteById(id);
    }
}