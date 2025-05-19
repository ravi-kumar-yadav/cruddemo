package com.ravi.cruddemo.service;

import com.ravi.cruddemo.entity.Employee;
import java.util.List;
import java.util.Map;

public interface EmployeeService {

    Employee findById(long employeeId);
    List<Employee> findAll();
    Employee save(Employee employee);
    void deleteById(long employeeId);
    List<Employee> findByFirstName(String firstName);
    List<Employee> findByLastName(String firstName);
    List<Employee> findByEmail(String firstName);

    Employee patchUpdate(Long employeeId, Map<String, Object> patchBody);
}
