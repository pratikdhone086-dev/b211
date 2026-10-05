package com.app.serviceimpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.app.entity.Fees;
import com.app.repository.FeesRepository;
import com.app.service.FeesService;

@Service
public class FeesServiceImpl implements FeesService {

    private final FeesRepository feesRepository;

    public FeesServiceImpl(FeesRepository feesRepository) {
        this.feesRepository = feesRepository;
    }

    @Override
    public Fees addFees(Fees fees) {
        return feesRepository.save(fees);
    }

    @Override
    public List<Fees> getAllFees() {
        return feesRepository.findAll();
    }

    @Override
    public Fees getFeesById(int id) {
        return feesRepository.findById(id).orElse(null);
    }

    @Override
    public Fees updateFees(Fees fees) {
        return feesRepository.save(fees);
    }

    @Override
    public void deleteFees(int id) {
        feesRepository.deleteById(id);
    }
}