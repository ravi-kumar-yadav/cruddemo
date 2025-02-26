package com.ravi.cruddemo.service;

import com.ravi.cruddemo.dao.EmployeeDAO;
import com.ravi.cruddemo.entity.Employee;
import com.ravi.cruddemo.exception.EmployeeNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    EmployeeDAO employeeDAO;

    @Autowired
    public EmployeeServiceImpl(EmployeeDAO theEmployeeDAO){
        this.employeeDAO = theEmployeeDAO;
    }

    /**
     * @return
     */
    @Override
    public List<Employee> fetchAll() {
        return employeeDAO.fetchAll();
    }

    /**
     * @param employeeId
     * @return
     */
    @Override
    public Employee fetchById(long employeeId) {
        Employee dbEmployee = employeeDAO.fetchById(employeeId);

        if (dbEmployee == null){
            throw new EmployeeNotFoundException("Employee not found for id: " + employeeId);
        }

        return dbEmployee;
    }

    /**
     * @param employee
     * @return
     */
    @Override
    @Transactional
    public Employee save(Employee employee) {
        return employeeDAO.save(employee);
    }

    /**
     * @param employeeId
     */
    @Override
    @Transactional
    public void deleteById(long employeeId) {
        employeeDAO.deleteById(employeeId);
    }

    /**
     * @param firstName
     * @return
     */
    @Override
    public List<Employee> findByFirstName(String firstName) {
        return employeeDAO.findByFirstName(firstName);
    }

    /**
     * @param lastName
     * @return
     */
    @Override
    public List<Employee> findByLastName(String lastName) {
        return employeeDAO.findByLastName(lastName);
    }

    /**
     * @param email
     * @return
     */
    @Override
    public List<Employee> findByEmail(String email) {
        return employeeDAO.findByEmail(email);
    }
}
