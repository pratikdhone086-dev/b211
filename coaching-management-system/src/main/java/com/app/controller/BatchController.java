
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

import com.app.entity.Batch;
import com.app.service.BatchService;

@RestController
@RequestMapping("/batches")
public class BatchController {

    private final BatchService batchService;

    public BatchController(BatchService batchService) {
        this.batchService = batchService;
    }

    @PostMapping
    public Batch addBatch(@RequestBody Batch batch) {
        return batchService.addBatch(batch);
    }

    @GetMapping("/{id}")
    public Batch getBatchById(@PathVariable int id) {

        Batch batch = batchService.getBatchById(id);

        if (batch == null) {
            throw new RuntimeException(
                    "Batch with ID " + id + " not found"
            );
        }

        return batch;
    }

    @GetMapping
    public List<Batch> getAllBatches() {
        return batchService.getAllBatches();
    }

    @PutMapping
    public Batch updateBatch(@RequestBody Batch batch) {

        Batch existingBatch =
                batchService.getBatchById(batch.getId());

        if (existingBatch == null) {
            throw new RuntimeException(
                    "Batch with ID "
                    + batch.getId()
                    + " not found"
            );
        }

        return batchService.updateBatch(batch);
    }

    @DeleteMapping("/{id}")
    public String deleteBatch(@PathVariable int id) {

        Batch batch = batchService.getBatchById(id);

        if (batch == null) {
            throw new RuntimeException(
                    "Batch with ID " + id + " not found"
            );
        }

        batchService.deleteBatch(id);

        return "Batch deleted successfully";
    }
}

