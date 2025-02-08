package com.ravi.cruddemo.dao;

import com.ravi.cruddemo.entity.Employee;

import java.util.List;

public interface EmployeeDAO {

    public List<Employee> findAll();
    public Employee findById(long employeeId);
    public Employee save(Employee employee);
    public void deleteById(long employeeId);

    public List<Employee> findByFirstName(String firstName);
    public List<Employee> findByLastName(String lastName);
    public List<Employee> findByEmail(String email);
}
