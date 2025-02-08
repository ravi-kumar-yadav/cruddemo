package com.ravi.cruddemo.service;

import com.ravi.cruddemo.entity.Employee;

import java.util.List;

public interface EmployeeService {

    public List<Employee> findAll();
    public Employee findById(Long employeeId);
    public Employee save(Employee employee);
    public void deleteById(Long employeeId);

    public List<Employee> findByFirstName(String firstName);
    public List<Employee> findByLastName(String lastName);
    public List<Employee> findByEmail(String email);
}
