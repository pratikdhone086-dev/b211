
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

import com.app.entity.Attendance;
import com.app.service.AttendanceService;

@RestController
@RequestMapping("/attendance")
public class AttendanceController {

    private final AttendanceService attendanceService;

    public AttendanceController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    @PostMapping
    public Attendance addAttendance(@RequestBody Attendance attendance) {
        return attendanceService.addAttendance(attendance);
    }

    @GetMapping
    public List<Attendance> getAllAttendance() {
        return attendanceService.getAllAttendance();
    }

    @GetMapping("/{id}")
    public Attendance getAttendanceById(@PathVariable int id) {

        Attendance attendance =
                attendanceService.getAttendanceById(id);

        if (attendance == null) {
            throw new RuntimeException(
                    "Attendance with ID " + id + " not found"
            );
        }

        return attendance;
    }

    @PutMapping
    public Attendance updateAttendance(
            @RequestBody Attendance attendance) {

        Attendance existingAttendance =
                attendanceService.getAttendanceById(
                        attendance.getId()
                );

        if (existingAttendance == null) {
            throw new RuntimeException(
                    "Attendance with ID "
                    + attendance.getId()
                    + " not found"
            );
        }

        return attendanceService.updateAttendance(attendance);
    }

    @DeleteMapping("/{id}")
    public String deleteAttendance(@PathVariable int id) {

        Attendance attendance =
                attendanceService.getAttendanceById(id);

        if (attendance == null) {
            throw new RuntimeException(
                    "Attendance with ID " + id + " not found"
            );
        }

        attendanceService.deleteAttendance(id);

        return "Attendance deleted successfully";
    }
}

