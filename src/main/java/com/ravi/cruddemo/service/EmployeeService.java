package com.ravi.cruddemo.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ravi.cruddemo.entity.Employee;

import java.util.List;
import java.util.Map;

public interface EmployeeService {

    Employee getById(long employeeId) throws JsonProcessingException;
    List<Employee> getAllEmployees() throws JsonProcessingException;
    Employee save(Employee employee) throws JsonProcessingException;
    void deleteById(long employeeId);
    List<Employee> fetchByFirstName(String firstName);
    List<Employee> fetchByLastName(String lastName);
    List<Employee> fetchByEmail(String email);
    List<Employee> fetchByEmailAndFirstName(String email, String firstName);
    Employee patchUpdate(long employeeId, Map<String, Object> patchBody);

}
