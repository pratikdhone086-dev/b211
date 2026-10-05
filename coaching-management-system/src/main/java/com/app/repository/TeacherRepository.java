package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entity.Teacher;

public interface TeacherRepository
        extends JpaRepository<Teacher, Integer> {

    Teacher findByUsername(String username);

}