package com.ravi.cruddemo.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.ravi.cruddemo.entity.Employee;
import com.ravi.cruddemo.exception.EmployeeNotFoundException;
import com.ravi.cruddemo.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    private static final Logger log = LoggerFactory.getLogger(EmployeeServiceImpl.class);
    private EmployeeRepository employeeRepository;
    private ObjectMapper objectMapper;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, ObjectMapper objectMapper){
        this.employeeRepository = employeeRepository;
        this.objectMapper = objectMapper;
    }
    /**
     * @param employeeId
     * @return
     */
    @Override
    public Employee getById(long employeeId) throws JsonProcessingException {
        log.info("Going to fetch employee for id: {}", employeeId);
        Optional<Employee> dbEmployee = employeeRepository.findById(employeeId);
        log.info("Fetched employee from DB: {}", objectMapper.writeValueAsString(dbEmployee));

        return dbEmployee.orElseThrow(() -> new EmployeeNotFoundException("Employee not found for id: " + employeeId));
    }

    /**
     * @return
     */
    @Override
    public List<Employee> getAllEmployees() throws JsonProcessingException {
        List<Employee> dbEmployees = employeeRepository.findAll();
        log.info("Fetched all employees from DB: {}", objectMapper.writeValueAsString(dbEmployees));

        return dbEmployees;
    }

    /**
     * @param employee
     * @return
     */
    @Override
    public Employee save(Employee employee) throws JsonProcessingException {
        Employee dbEmployee = employeeRepository.save(employee);

        log.info("Saved employee in DB: {}", objectMapper.writeValueAsString(dbEmployee));

        return dbEmployee;
    }

    /**
     * @param employeeId
     * @return
     */
    @Override
    public void deleteById(long employeeId) {
        log.info("Going to delete employee with id: {}", employeeId);
        employeeRepository.deleteById(employeeId);
        log.info("Remove employee with id: {}", employeeId);
    }

    /**
     * @param firstName
     * @return
     */
    @Override
    public List<Employee> fetchByFirstName(String firstName) {
        log.info("Going to fetch employee by firstName: {}", firstName);
        return employeeRepository.fetchByFirstName(firstName);
    }

    /**
     * @param lastName
     * @return
     */
    @Override
    public List<Employee> fetchByLastName(String lastName) {
        log.info("Going to fetch employee by lastName: {}", lastName);
        return employeeRepository.fetchByLastName(lastName);
    }

    /**
     * @param email
     * @return
     */
    @Override
    public List<Employee> fetchByEmail(String email) {
        log.info("Going to fetch employee by email: {}", email);
        return employeeRepository.fetchByEmail(email);
    }

    /**
     * @param email
     * @param firstName
     * @return
     */
    @Override
    public List<Employee> fetchByEmailAndFirstName(String email, String firstName) {
        log.info("Going to fetch employee by email: {} and firstName: {}", email, firstName);
        return employeeRepository.fetchByEmailAndFirstName(email, firstName);
    }

    /**
     * @param employeeId
     * @param patchBody
     * @return
     */
    @Override
    public Employee patchUpdate(long employeeId, Map<String, Object> patchBody) {
        Optional<Employee> dbEmployee = employeeRepository.findById(employeeId);

        if (patchBody.containsKey("id")){
            throw new RuntimeException("Patch request can't  have field:id in body");
        }

        if(dbEmployee.isPresent()){
            Employee toBeUpdatedEmployee = toBeUpdatedEmployee(dbEmployee.get(), patchBody);

            return employeeRepository.save(toBeUpdatedEmployee);
        } else {
            throw new EmployeeNotFoundException("Employee not found for id: " + employeeId);
        }
    }

    private Employee toBeUpdatedEmployee(Employee dbEmployee, Map<String, Object> patchBody) {
        ObjectNode employeeNode = objectMapper.convertValue(dbEmployee, ObjectNode.class);
        ObjectNode patchNode = objectMapper.convertValue(patchBody, ObjectNode.class);

        employeeNode.setAll(patchNode);

        return objectMapper.convertValue(employeeNode, Employee.class);
    }
}
