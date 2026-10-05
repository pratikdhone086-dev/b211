package com.app.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.app.entity.Teacher;
import com.app.repository.TeacherRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final TeacherRepository teacherRepository;

    public CustomUserDetailsService(
            TeacherRepository teacherRepository) {

        this.teacherRepository = teacherRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        Teacher teacher =
                teacherRepository.findByUsername(username);

        if (teacher == null) {
            throw new UsernameNotFoundException(
                    "Teacher not found"
            );
        }

        return User.builder()
                .username(teacher.getUsername())
                .password(teacher.getPassword())
                .roles("TEACHER")
                .build();
    }
}