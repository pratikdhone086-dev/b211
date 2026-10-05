package com.app.service;

import java.util.List;

import com.app.entity.Attendance;

public interface AttendanceService {

    Attendance addAttendance(Attendance attendance);

    List<Attendance> getAllAttendance();

    Attendance getAttendanceById(int id);

    Attendance updateAttendance(Attendance attendance);

    void deleteAttendance(int id);
}