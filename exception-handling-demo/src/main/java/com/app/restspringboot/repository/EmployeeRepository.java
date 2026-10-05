package com.app.restspringboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.restspringboot.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}