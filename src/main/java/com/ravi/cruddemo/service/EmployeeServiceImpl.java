package com.ravi.cruddemo.service;

import com.ravi.cruddemo.dao.EmployeeDAO;
import com.ravi.cruddemo.dao.EmployeeDAOImpl;
import com.ravi.cruddemo.entity.Employee;
import com.ravi.cruddemo.exception.EmployeeNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService{
    private EmployeeDAO employeeDAO;

    @Autowired
    public EmployeeServiceImpl(EmployeeDAO theEmployeeDAO){
        employeeDAO = theEmployeeDAO;
    }

    @Override
    public List<Employee> findAll() {
        return employeeDAO.findAll();
    }

    @Override
    public Employee findById(long employeeId) {
        Employee dbEmployee = employeeDAO.findById(employeeId);

        if (dbEmployee == null){
            throw new EmployeeNotFoundException("Employee not found for id: " + employeeId);
        }

        return dbEmployee;
    }

    @Override
    @Transactional
    public Employee save(Employee employee) {
        return employeeDAO.save(employee);
    }

    @Override
    @Transactional
    public void deleteById(long employeeId) {
        employeeDAO.deleteById(employeeId);
    }

    @Override
    public List<Employee> findByFirstName(String firstName) {
        List<Employee> dbEmployee = employeeDAO.findByFirstName(firstName);

        if (dbEmployee == null || dbEmployee.size() == 0){
            throw new EmployeeNotFoundException("No Employee found for firstName: " + firstName);
        } else {
            return dbEmployee;
        }
    }

    @Override
    public List<Employee> findByLastName(String lastName) {
        List<Employee> dbEmployee = employeeDAO.findByLastName(lastName);

        if (dbEmployee == null || dbEmployee.size() == 0){
            throw new EmployeeNotFoundException("No Employee found for lastName: " + lastName);
        } else {
            return dbEmployee;
        }
    }

    @Override
    public List<Employee> findByEmail(String email) {
        List<Employee> dbEmployee = employeeDAO.findByEmail(email);

        if (dbEmployee == null || dbEmployee.size() == 0){
            throw new EmployeeNotFoundException("No Employee found for Email: " + email);
        } else {
            return dbEmployee;
        }
    }
}
