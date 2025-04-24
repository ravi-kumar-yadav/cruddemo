package com.ravi.cruddemo.service;

import com.ravi.cruddemo.entity.Employee;

import java.util.List;
import java.util.Map;

public interface EmployeeService {
    List<Employee> findAll();
    Employee findById(long employeeId);
    Employee save(Employee employee);
    Employee deleteById(long employeeId);
    Employee patchUpdate(Map<String, Object> patchBody);
}
