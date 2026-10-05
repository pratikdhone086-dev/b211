package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entity.Attendance;

public interface AttendanceRepository
        extends JpaRepository<Attendance, Integer> {

}