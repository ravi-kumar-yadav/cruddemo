package com.ravi.cruddemo.service;

import com.ravi.cruddemo.entity.Employee;
import com.ravi.cruddemo.exception.EmployeeNotFoundException;
import com.ravi.cruddemo.repository.EmployeeJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    private EmployeeJpaRepository employeeJpaRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeJpaRepository theEmployeeJpaRepository){
        this.employeeJpaRepository = theEmployeeJpaRepository;
    }

    /**
     * @return
     */
    @Override
    public List<Employee> findAll() {
        return employeeJpaRepository.findAll();
    }

    /**
     * @param employeeId
     * @return
     */
    @Override
    public Employee findById(long employeeId) {
        Optional<Employee> dbEmployee = employeeJpaRepository.findById(employeeId);

        if (dbEmployee.isPresent()) {
            return dbEmployee.get();
        } else {
            throw new EmployeeNotFoundException("Employee not found for id: " + employeeId);
        }
    }

    /**
     * @param employee
     * @return
     */
    @Override
    public Employee save(Employee employee) {
        return employeeJpaRepository.save(employee);
    }

    /**
     * @param employeeId
     */
    @Override
    public void deleteById(long employeeId) {
        employeeJpaRepository.deleteById(employeeId);
    }

    /**
     * @param firstName
     * @return
     */
    @Override
    public List<Employee> findByFirstName(String firstName) {
        return employeeJpaRepository.findByFirstName(firstName);
    }

    /**
     * @param lastName
     * @return
     */
    @Override
    public List<Employee> findByLastName(String lastName) {
        return employeeJpaRepository.findByLastName(lastName);
    }

    /**
     * @param email
     * @return
     */
    @Override
    public List<Employee> findByEmail(String email) {
        return employeeJpaRepository.findByEmail(email);
    }
}
