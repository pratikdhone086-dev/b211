package com.app.serviceimpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.app.entity.Marks;
import com.app.repository.MarksRepository;
import com.app.service.MarksService;

@Service
public class MarksServiceImpl implements MarksService {

    private final MarksRepository marksRepository;

    public MarksServiceImpl(MarksRepository marksRepository) {
        this.marksRepository = marksRepository;
    }

    @Override
    public Marks addMarks(Marks marks) {
        return marksRepository.save(marks);
    }

    @Override
    public List<Marks> getAllMarks() {
        return marksRepository.findAll();
    }

    @Override
    public Marks getMarksById(int id) {
        return marksRepository.findById(id).orElse(null);
    }

    @Override
    public Marks updateMarks(Marks marks) {
        return marksRepository.save(marks);
    }

    @Override
    public void deleteMarks(int id) {
        marksRepository.deleteById(id);
    }
}