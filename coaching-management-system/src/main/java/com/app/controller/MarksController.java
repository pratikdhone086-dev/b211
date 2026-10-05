
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

import com.app.entity.Marks;
import com.app.service.MarksService;

@RestController
@RequestMapping("/marks")
public class MarksController {

    private final MarksService marksService;

    public MarksController(MarksService marksService) {
        this.marksService = marksService;
    }

    @PostMapping
    public Marks addMarks(@RequestBody Marks marks) {
        return marksService.addMarks(marks);
    }

    @GetMapping
    public List<Marks> getAllMarks() {
        return marksService.getAllMarks();
    }

    @GetMapping("/{id}")
    public Marks getMarksById(@PathVariable int id) {

        Marks marks = marksService.getMarksById(id);

        if (marks == null) {
            throw new RuntimeException(
                    "Marks with ID " + id + " not found"
            );
        }

        return marks;
    }

    @PutMapping
    public Marks updateMarks(@RequestBody Marks marks) {

        Marks existingMarks =
                marksService.getMarksById(marks.getId());

        if (existingMarks == null) {
            throw new RuntimeException(
                    "Marks with ID "
                    + marks.getId()
                    + " not found"
            );
        }

        return marksService.updateMarks(marks);
    }

    @DeleteMapping("/{id}")
    public String deleteMarks(@PathVariable int id) {

        Marks marks = marksService.getMarksById(id);

        if (marks == null) {
            throw new RuntimeException(
                    "Marks with ID " + id + " not found"
            );
        }

        marksService.deleteMarks(id);

        return "Marks deleted successfully";
    }
}

