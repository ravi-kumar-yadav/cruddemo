package com.ravi.cruddemo.service;

import com.ravi.cruddemo.dao.EmployeeJPARepository;
import com.ravi.cruddemo.entity.Employee;
import com.ravi.cruddemo.exception.EmployeeNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    private EmployeeJPARepository employeeJPARepository;


    @Autowired
    public EmployeeServiceImpl(EmployeeJPARepository theEmployeeJPARepository){
        employeeJPARepository = theEmployeeJPARepository;
    }


    /**
     * @return
     */
    @Override
    public List<Employee> findAll() {
        return employeeJPARepository.findAll();
    }

    /**
     * @param employeeId
     * @return
     */
    @Override
    public Employee findById(long employeeId) {
        Optional<Employee> dbEmployee = employeeJPARepository.findById(employeeId);

        if (dbEmployee.isPresent()){
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
        return employeeJPARepository.save(employee);
    }

    /**
     * @param employeeId
     */
    @Override
    public void deleteById(long employeeId) {
        Optional<Employee> dbEmployee = employeeJPARepository.findById(employeeId);

        if (dbEmployee.isPresent()){
            employeeJPARepository.delete(dbEmployee.get());
        } else {
            throw new EmployeeNotFoundException("Employee not found with id: " + employeeId);
        }
    }

    /**
     * @param firstName
     * @return
     */
    @Override
    public List<Employee> findByFirstName(String firstName) {
        return employeeJPARepository.findByFirstName(firstName);
    }

    /**
     * @param lastName
     * @return
     */
    @Override
    public List<Employee> findByLastName(String lastName) {
        return employeeJPARepository.findByLastName(lastName);
    }

    /**
     * @param email
     * @return
     */
    @Override
    public List<Employee> findByEmail(String email) {
        return employeeJPARepository.findByEmail(email);
    }

    /**
     * @param firstName
     * @param email
     * @return
     */
    @Override
    public List<Employee> findByFirstNameAndEmail(String firstName, String email) {
        return employeeJPARepository.findByFirstNameAndEmail(firstName, email);
    }
}
