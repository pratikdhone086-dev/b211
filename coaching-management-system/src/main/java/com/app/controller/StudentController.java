
package com.app.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.app.entity.Student;
import com.app.service.StudentService;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public Student addStudent(@RequestBody Student student) {
        return studentService.addStudent(student);
    }

    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable int id) {

        Student student = studentService.getStudentById(id);

        if (student == null) {
            throw new RuntimeException(
                    "Student with ID " + id + " not found"
            );
        }

        return student;
    }

    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @PutMapping
    public Student updateStudent(@RequestBody Student student) {
        return studentService.updateStudent(student);
    }

    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable int id) {

        Student student = studentService.getStudentById(id);

        if (student == null) {
            throw new RuntimeException(
                    "Student with ID " + id + " not found"
            );
        }

        studentService.deleteStudent(id);

        return "Student deleted successfully";
    }

    @PostMapping("/{id}/photo")
    public ResponseEntity<String> uploadPhoto(
            @PathVariable int id,
            @RequestParam("photo") MultipartFile file) {

        try {

            Student student = studentService.getStudentById(id);

            if (student == null) {
                return ResponseEntity
                        .status(404)
                        .body("Student with ID " + id + " not found");
            }

            if (file.isEmpty()) {
                return ResponseEntity
                        .badRequest()
                        .body("Please select a photo");
            }

            String folder = "uploads/students";

            Path uploadPath = Paths.get(folder);

            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            String originalFileName =
                    file.getOriginalFilename();

            String extension = "";

            if (originalFileName != null
                    && originalFileName.contains(".")) {

                extension =
                        originalFileName.substring(
                                originalFileName.lastIndexOf(".")
                        );
            }

            String fileName =
                    UUID.randomUUID().toString() + extension;

            Path filePath =
                    uploadPath.resolve(fileName);

            Files.copy(
                    file.getInputStream(),
                    filePath
            );

            student.setPhoto(
                    "/uploads/students/" + fileName
            );

            studentService.updateStudent(student);

            return ResponseEntity.ok(
                    "Photo uploaded successfully"
            );

        } catch (IOException e) {

            return ResponseEntity
                    .internalServerError()
                    .body("Photo upload failed");
        }
    }
}

