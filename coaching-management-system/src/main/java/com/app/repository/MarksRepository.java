package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entity.Marks;

public interface MarksRepository extends JpaRepository<Marks, Integer> {

}