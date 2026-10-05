package com.app.service;

import java.util.List;

import com.app.entity.Batch;

public interface BatchService {

    Batch addBatch(Batch batch);

    List<Batch> getAllBatches();

    Batch getBatchById(int id);

    Batch updateBatch(Batch batch);

    void deleteBatch(int id);
}