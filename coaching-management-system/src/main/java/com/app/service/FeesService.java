package com.app.service;

import java.util.List;

import com.app.entity.Fees;

public interface FeesService {

    Fees addFees(Fees fees);

    List<Fees> getAllFees();

    Fees getFeesById(int id);

    Fees updateFees(Fees fees);

    void deleteFees(int id);
}