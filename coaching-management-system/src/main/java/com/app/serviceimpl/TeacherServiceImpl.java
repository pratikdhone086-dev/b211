package com.app.serviceimpl;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.entity.Teacher;
import com.app.repository.TeacherRepository;
import com.app.service.TeacherService;

@Service
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;

    private final PasswordEncoder passwordEncoder;

    public TeacherServiceImpl(
            TeacherRepository teacherRepository,
            PasswordEncoder passwordEncoder) {

        this.teacherRepository = teacherRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Teacher addTeacher(Teacher teacher) {

        teacher.setPassword(
                passwordEncoder.encode(
                        teacher.getPassword()
                )
        );

        return teacherRepository.save(teacher);
    }

    @Override
    public Teacher login(String username, String password) {

        Teacher teacher =
                teacherRepository.findByUsername(username);

        if (teacher == null) {
            return null;
        }

        if (passwordEncoder.matches(
                password,
                teacher.getPassword())) {

            return teacher;
        }

        return null;
    }

    @Override
    public List<Teacher> getAllTeachers() {

        return teacherRepository.findAll();
    }

    @Override
    public Teacher getTeacherById(int id) {

        return teacherRepository
                .findById(id)
                .orElse(null);
    }

    @Override
    public Teacher updateTeacher(Teacher teacher) {

        Teacher existingTeacher =
                teacherRepository
                        .findById(teacher.getId())
                        .orElse(null);

        if (existingTeacher == null) {
            return null;
        }

        existingTeacher.setName(
                teacher.getName()
        );

        existingTeacher.setEmail(
                teacher.getEmail()
        );

        existingTeacher.setUsername(
                teacher.getUsername()
        );

        if (teacher.getPassword() != null
                && !teacher.getPassword().isEmpty()) {

            existingTeacher.setPassword(
                    passwordEncoder.encode(
                            teacher.getPassword()
                    )
            );
        }

        return teacherRepository.save(existingTeacher);
    }

    @Override
    public void deleteTeacher(int id) {

        teacherRepository.deleteById(id);
    }
}