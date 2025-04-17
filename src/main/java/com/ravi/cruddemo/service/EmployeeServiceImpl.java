package com.ravi.cruddemo.service;

import com.ravi.cruddemo.dao.EmployeeJPARepository;
import com.ravi.cruddemo.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return employeeJPARepository.findById(employeeId);
    }

    /**
     * @param employee
     * @return
     */
    @Override
    public Employee save(Employee employee) {
        return null;
    }

    /**
     * @param employeeId
     */
    @Override
    public void deleteById(long employeeId) {

    }

    /**
     * @param firstName
     * @return
     */
    @Override
    public List<Employee> findByFirstName(String firstName) {
        return List.of();
    }

    /**
     * @param lastName
     * @return
     */
    @Override
    public List<Employee> findByLastName(String lastName) {
        return List.of();
    }

    /**
     * @param email
     * @return
     */
    @Override
    public List<Employee> findByEmail(String email) {
        return List.of();
    }

    /**
     * @param firstName
     * @param email
     * @return
     */
    @Override
    public List<Employee> findByFirstNameAndEmail(String firstName, String email) {
        return List.of();
    }
}
