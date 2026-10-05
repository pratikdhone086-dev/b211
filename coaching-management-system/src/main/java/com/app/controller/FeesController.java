
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

import com.app.entity.Fees;
import com.app.service.FeesService;

@RestController
@RequestMapping("/fees")
public class FeesController {

    private final FeesService feesService;

    public FeesController(FeesService feesService) {
        this.feesService = feesService;
    }

    @PostMapping
    public Fees addFees(@RequestBody Fees fees) {
        return feesService.addFees(fees);
    }

    @GetMapping
    public List<Fees> getAllFees() {
        return feesService.getAllFees();
    }

    @GetMapping("/{id}")
    public Fees getFeesById(@PathVariable int id) {

        Fees fees = feesService.getFeesById(id);

        if (fees == null) {
            throw new RuntimeException(
                    "Fees with ID " + id + " not found"
            );
        }

        return fees;
    }

    @PutMapping
    public Fees updateFees(@RequestBody Fees fees) {

        Fees existingFees =
                feesService.getFeesById(fees.getId());

        if (existingFees == null) {
            throw new RuntimeException(
                    "Fees with ID " + fees.getId() + " not found"
            );
        }

        return feesService.updateFees(fees);
    }

    @DeleteMapping("/{id}")
    public String deleteFees(@PathVariable int id) {

        Fees fees = feesService.getFeesById(id);

        if (fees == null) {
            throw new RuntimeException(
                    "Fees with ID " + id + " not found"
            );
        }

        feesService.deleteFees(id);

        return "Fees deleted successfully";
    }
}

