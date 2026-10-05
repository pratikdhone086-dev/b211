package com.app.restspringboot.service;

import java.util.List;

import com.app.restspringboot.entity.Employee;

public interface EmployeeService {

    Employee addEmployee(Employee employee);

    Employee getEmployee(int id);

    List<Employee> getAllEmployees();

    String deleteEmployee(int id);
}