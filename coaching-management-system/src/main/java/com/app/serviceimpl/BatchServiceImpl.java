package com.app.serviceimpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.app.entity.Batch;
import com.app.repository.BatchRepository;
import com.app.service.BatchService;

@Service
public class BatchServiceImpl implements BatchService {

    private final BatchRepository batchRepository;

    public BatchServiceImpl(BatchRepository batchRepository) {
        this.batchRepository = batchRepository;
    }

    @Override
    public Batch addBatch(Batch batch) {
        return batchRepository.save(batch);
    }

    @Override
    public List<Batch> getAllBatches() {
        return batchRepository.findAll();
    }

    @Override
    public Batch getBatchById(int id) {
        return batchRepository.findById(id).orElse(null);
    }

    @Override
    public Batch updateBatch(Batch batch) {
        return batchRepository.save(batch);
    }

    @Override
    public void deleteBatch(int id) {
        batchRepository.deleteById(id);
    }
}