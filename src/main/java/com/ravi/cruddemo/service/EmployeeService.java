package com.ravi.cruddemo.service;

import com.ravi.cruddemo.entity.Employee;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeService {

    List<Employee> findAll();
    Employee findById(long employeeId);
    Employee save(Employee employee);
    void deleteById(long employeeId);

    List<Employee> findByFirstName(String firstName);
    List<Employee> findByLastName(String lastName);
    List<Employee> findByEmail(String email);
}
