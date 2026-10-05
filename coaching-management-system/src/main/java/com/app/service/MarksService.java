package com.app.service;

import java.util.List;

import com.app.entity.Marks;

public interface MarksService {

    Marks addMarks(Marks marks);

    List<Marks> getAllMarks();

    Marks getMarksById(int id);

    Marks updateMarks(Marks marks);

    void deleteMarks(int id);
}