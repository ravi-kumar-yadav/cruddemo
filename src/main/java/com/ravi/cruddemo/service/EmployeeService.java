package com.ravi.cruddemo.service;

import com.ravi.cruddemo.entity.Employee;

import java.util.List;

public interface EmployeeService {
    public List<Employee> fetchAll();
    public Employee fetchById(long employeeId);
    public Employee save(Employee employee);
    public void deleteById(long employeeId);

    List<Employee> findByFirstName(String firstName);
    List<Employee> findByLastName(String lastName);
    List<Employee> findByEmail(String email);
}
