package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entity.Fees;

public interface FeesRepository extends JpaRepository<Fees, Integer> {

}