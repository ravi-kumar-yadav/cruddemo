package com.ravi.cruddemo.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.ravi.cruddemo.dao.EmployeeJpaRepository;
import com.ravi.cruddemo.entity.Employee;
import com.ravi.cruddemo.exception.EmployeeNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    private EmployeeJpaRepository employeeJpaRepository;
    private ObjectMapper objectMapper;

    @Autowired
    public EmployeeServiceImpl(EmployeeJpaRepository theEmployeeJpaRepository, ObjectMapper theObjectMapper){
        employeeJpaRepository = theEmployeeJpaRepository;
        objectMapper = theObjectMapper;
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
        return employeeJpaRepository.save(employee);
    }

    /**
     * @param employeeId
     */
    @Override
    public void deleteById(long employeeId) {
        Optional<Employee> dbEmployee = employeeJpaRepository.findById(employeeId);

        if (dbEmployee.isPresent()){
            employeeJpaRepository.delete(dbEmployee.get());
        } else {
            throw new EmployeeNotFoundException("Employee not found for id: " + employeeId);
        }
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

    /**
     * @param firstName
     * @param email
     * @return
     */
    @Override
    public List<Employee> findByFirstNameAndEmail(String firstName, String email) {
        return employeeJpaRepository.findByFirstNameAndEmail(firstName, email);
    }

    /**
     * @param lastName
     * @param email
     * @return
     */
    @Override
    public List<Employee> findByLastNameAndEmail(String lastName, String email) {
        return employeeJpaRepository.findByLastNameAndEmail(lastName, email);
    }

    /**
     * @param employeeId
     * @param patchBody
     * @return
     */
    @Override
    public Employee patchUpdate(long employeeId, Map<String, Object> patchBody) {
       Optional<Employee> dbEmployee = employeeJpaRepository.findById(employeeId);

       if(!dbEmployee.isPresent()){
           throw new EmployeeNotFoundException("Employee not found for id: " + employeeId);
       }

       if (patchBody.containsKey("id")){
           throw new RuntimeException("Patch Body shouldn't contain 'id'");
       }

       Employee toBeUpdated = applyPatch(dbEmployee, patchBody);

       return employeeJpaRepository.save(toBeUpdated);
    }

    private Employee applyPatch(Optional<Employee> dbEmployee, Map<String, Object> patchBody) {
        ObjectNode employeeNode = objectMapper.convertValue(dbEmployee, ObjectNode.class);
        ObjectNode patchNode = objectMapper.convertValue(patchBody, ObjectNode.class);

        employeeNode.setAll(patchNode);

        return objectMapper.convertValue(employeeNode, Employee.class);
    }


}
