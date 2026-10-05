package com.app.controller;

import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.entity.Teacher;
import com.app.service.TeacherService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/teachers")
public class TeacherController {

    private final TeacherService teacherService;

    private final SecurityContextRepository securityContextRepository =
            new HttpSessionSecurityContextRepository();

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    // =========================
    // REGISTER TEACHER
    // =========================

    @PostMapping("/register")
    public Teacher addTeacher(
            @RequestBody Teacher teacher) {

        return teacherService.addTeacher(teacher);
    }

    // =========================
    // LOGIN
    // =========================

    @PostMapping("/login")
    public Teacher login(
            @RequestBody Teacher teacher,
            HttpServletRequest request,
            HttpServletResponse response) {

        Teacher loggedInTeacher =
                teacherService.login(
                        teacher.getUsername(),
                        teacher.getPassword()
                );

        if (loggedInTeacher == null) {

            throw new RuntimeException(
                    "Invalid username or password"
            );
        }

        Authentication authentication =
                new UsernamePasswordAuthenticationToken(
                        loggedInTeacher.getUsername(),
                        null,
                        List.of(
                                new org.springframework
                                .security.core.authority
                                .SimpleGrantedAuthority(
                                        "ROLE_TEACHER"
                                )
                        )
                );

        SecurityContext securityContext =
                SecurityContextHolder
                        .createEmptyContext();

        securityContext.setAuthentication(
                authentication
        );

        SecurityContextHolder.setContext(
                securityContext
        );

        securityContextRepository.saveContext(
                securityContext,
                request,
                response
        );

        return loggedInTeacher;
    }

    // =========================
    // LOGOUT
    // =========================

    @PostMapping("/logout")
    public String logout(
            HttpServletRequest request) {

        HttpSession session =
                request.getSession(false);

        if (session != null) {
            session.invalidate();
        }

        SecurityContextHolder.clearContext();

        return "Logout successful";
    }

    // =========================
    // GET ALL TEACHERS
    // =========================

    @GetMapping
    public List<Teacher> getAllTeachers() {

        return teacherService.getAllTeachers();
    }

    // =========================
    // GET TEACHER BY ID
    // =========================

    @GetMapping("/{id}")
    public Teacher getTeacherById(
            @PathVariable int id) {

        Teacher teacher =
                teacherService.getTeacherById(id);

        if (teacher == null) {

            throw new RuntimeException(
                    "Teacher with ID "
                    + id
                    + " not found"
            );
        }

        return teacher;
    }

    // =========================
    // UPDATE TEACHER
    // =========================

    @PutMapping
    public Teacher updateTeacher(
            @RequestBody Teacher teacher) {

        Teacher existingTeacher =
                teacherService.getTeacherById(
                        teacher.getId()
                );

        if (existingTeacher == null) {

            throw new RuntimeException(
                    "Teacher with ID "
                    + teacher.getId()
                    + " not found"
            );
        }

        return teacherService.updateTeacher(
                teacher
        );
    }

    // =========================
    // DELETE TEACHER
    // =========================

    @DeleteMapping("/{id}")
    public String deleteTeacher(
            @PathVariable int id) {

        Teacher teacher =
                teacherService.getTeacherById(id);

        if (teacher == null) {

            throw new RuntimeException(
                    "Teacher with ID "
                    + id
                    + " not found"
            );
        }

        teacherService.deleteTeacher(id);

        return "Teacher deleted successfully";
    }
}