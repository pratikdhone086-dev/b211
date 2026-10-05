package com.app.serviceimpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.app.entity.Attendance;
import com.app.repository.AttendanceRepository;
import com.app.service.AttendanceService;

@Service
public class AttendanceServiceImpl implements AttendanceService {

    private final AttendanceRepository attendanceRepository;

    public AttendanceServiceImpl(
            AttendanceRepository attendanceRepository) {

        this.attendanceRepository = attendanceRepository;
    }

    @Override
    public Attendance addAttendance(
            Attendance attendance) {

        return attendanceRepository.save(attendance);
    }

    @Override
    public List<Attendance> getAllAttendance() {

        return attendanceRepository.findAll();
    }

    @Override
    public Attendance getAttendanceById(int id) {

        return attendanceRepository
                .findById(id)
                .orElse(null);
    }

    @Override
    public Attendance updateAttendance(
            Attendance attendance) {

        Attendance existingAttendance =
                attendanceRepository
                        .findById(attendance.getId())
                        .orElse(null);

        if (existingAttendance == null) {

            throw new RuntimeException(
                    "Attendance with ID "
                    + attendance.getId()
                    + " not found"
            );
        }

        existingAttendance.setStudentId(
                attendance.getStudentId()
        );

        existingAttendance.setDate(
                attendance.getDate()
        );

        existingAttendance.setStatus(
                attendance.getStatus()
        );

        return attendanceRepository.save(
                existingAttendance
        );
    }

    @Override
    public void deleteAttendance(int id) {

        attendanceRepository.deleteById(id);
    }
}