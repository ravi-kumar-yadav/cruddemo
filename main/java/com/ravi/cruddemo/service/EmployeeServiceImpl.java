package com.ravi.cruddemo.service;

import com.ravi.cruddemo.dao.EmployeeJpaRepository;
import com.ravi.cruddemo.entity.Employee;
import com.ravi.cruddemo.exceptionhandler.EmployeeNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService{
    private EmployeeJpaRepository employeeJpaRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeJpaRepository theEmployeeJpaRepository){
        employeeJpaRepository = theEmployeeJpaRepository;
    }

    @Override
    public List<Employee> findAll() {
        return employeeJpaRepository.findAll();
    }

    @Override
    public Employee findById(Long employeeId) {
        Optional<Employee> dbEmployee = employeeJpaRepository.findById(employeeId);

        if (!dbEmployee.isPresent()){
            throw  new EmployeeNotFoundException("No Employee Found for employeeId: " + employeeId);
        }

        return dbEmployee.get();
    }

    @Override
    public Employee save(Employee employee) {
        return employeeJpaRepository.save(employee);
    }

    @Override
    public void deleteById(Long employeeId) {
        Optional<Employee> dbEmployee = employeeJpaRepository.findById(employeeId);

        if (dbEmployee.isPresent()){
            employeeJpaRepository.deleteById(employeeId);
        } else {
            throw new EmployeeNotFoundException("Employee not found with employeeId: " + employeeId);
        }
    }

    @Override
    public List<Employee> findByFirstName(String firstName) {
        List<Employee> dbEmployees = employeeJpaRepository.findByFirstName(firstName);
        if(dbEmployees == null || dbEmployees.size() == 0){
            throw new EmployeeNotFoundException("No employee found for firstName: " + firstName);
        }

        return dbEmployees;
    }

    @Override
    public List<Employee> findByLastName(String lastName) {
        List<Employee> dbEmployees = employeeJpaRepository.findByLastName(lastName);
        if(dbEmployees == null || dbEmployees.size() == 0){
            throw new EmployeeNotFoundException("No employee found for lastName: " + lastName);
        }

        return dbEmployees;
    }

    @Override
    public List<Employee> findByEmail(String email) {
        List<Employee> dbEmployees = employeeJpaRepository.findByEmail(email);
        if(dbEmployees == null || dbEmployees.size() == 0){
            throw new EmployeeNotFoundException("No employee found for email: " + email);
        }

        return dbEmployees;
    }
}
