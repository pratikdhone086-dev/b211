package com.app.restspringboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.restspringboot.entity.Employee;
import com.app.restspringboot.exception.DuplicateEmployeeException;
import com.app.restspringboot.exception.EmployeeNotFoundException;
import com.app.restspringboot.exception.InvalidEmployeeException;
import com.app.restspringboot.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository repository;

    @Override
    public Employee addEmployee(Employee employee) {

        if (employee.getId() <= 0) {
            throw new InvalidEmployeeException(
                    "Employee ID must be greater than 0");
        }

        if (employee.getName() == null || employee.getName().trim().isEmpty()) {
            throw new InvalidEmployeeException(
                    "Employee name cannot be empty");
        }

        if (employee.getSalary() <= 0) {
            throw new InvalidEmployeeException(
                    "Salary must be greater than 0");
        }

        if (repository.existsById(employee.getId())) {
            throw new DuplicateEmployeeException(
                    "Employee already exists with id: " + employee.getId());
        }

        return repository.save(employee);
    }

    @Override
    public Employee getEmployee(int id) {

        return repository.findById(id)
                .orElseThrow(() ->
                        new EmployeeNotFoundException(
                                "Employee not found with id: " + id));
    }

    @Override
    public List<Employee> getAllEmployees() {

        return repository.findAll();
    }

    @Override
    public String deleteEmployee(int id) {

        if (!repository.existsById(id)) {
            throw new EmployeeNotFoundException(
                    "Employee not found with id: " + id);
        }

        repository.deleteById(id);

        return "Employee deleted successfully";
    }
}