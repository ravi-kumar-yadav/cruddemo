package com.ravi.cruddemo.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.ravi.cruddemo.entity.Employee;
import com.ravi.cruddemo.exception.EmployeeNotFoundException;
import com.ravi.cruddemo.repository.EmployeeJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.print.attribute.standard.MediaSize;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    private EmployeeJpaRepository employeeJpaRepository;
    private ObjectMapper objectMapper;

    @Autowired
    public EmployeeServiceImpl(EmployeeJpaRepository employeeJpaRepository, ObjectMapper objectMapper){
        this.employeeJpaRepository = employeeJpaRepository;
        this.objectMapper = objectMapper;
    }

    /**
     * @param employeeId
     * @return
     */
    @Override
    public Employee findById(long employeeId) {
        Optional<Employee> dbEmployee = employeeJpaRepository.findById(employeeId);

        if(dbEmployee.isPresent()){
            return dbEmployee.get();
        } else {
            throw new EmployeeNotFoundException("Employee not found for employeeId: " + employeeId);
        }
    }

    /**
     * @return
     */
    @Override
    public List<Employee> findAll() {
        return employeeJpaRepository.findAll();
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
     * @return
     */
    @Override
    public void deleteById(long employeeId) {
        Optional<Employee> dbEmployee = employeeJpaRepository.findById(employeeId);

        if (!dbEmployee.isPresent()){
            throw new EmployeeNotFoundException("Employee not found for employee_id: " + employeeId);
        }
        employeeJpaRepository.deleteById(employeeId);

        return;
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
     * @param employeeId
     * @param patchBody
     * @return
     */
    @Override
    public Employee patchUpdate(Long employeeId, Map<String, Object> patchBody) {
        Optional<Employee> dbEmployee = employeeJpaRepository.findById(employeeId);

        if (!dbEmployee.isPresent()){
            throw new EmployeeNotFoundException("Employee not found for employeeId: " + employeeId);
        }

        Employee toBeSaved = applyPatch(dbEmployee.get(), patchBody);

        return employeeJpaRepository.save(toBeSaved);
    }

    private Employee applyPatch(Employee dbEmployee, Map<String, Object> patchBody) {

        ObjectNode employeeNode = objectMapper.convertValue(dbEmployee, ObjectNode.class);
        ObjectNode patchNode = objectMapper.convertValue(patchBody, ObjectNode.class);

        employeeNode.setAll(patchNode);

        return objectMapper.convertValue(employeeNode, Employee.class);
    }
}
