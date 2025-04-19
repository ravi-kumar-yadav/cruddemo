package com.ravi.cruddemo.service;


import com.ravi.cruddemo.entity.Employee;

import java.util.List;
import java.util.Map;

public interface EmployeeService {

    List<Employee> findAll();
    Employee findById(long employeeId);

    Employee save(Employee employee);
    void deleteById(long employeeId);

    List<Employee> findByFirstName(String firstName);
    List<Employee> findByLastName(String lastName);
    List<Employee> findByEmail(String email);
    List<Employee> findByFirstNameAndEmail(String firstName, String email);
    List<Employee> findByLastNameAndEmail(String lastName, String email);

    Employee patchUpdate(long employeeId, Map<String, Object> patchBody);

}
