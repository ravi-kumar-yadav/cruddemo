package com.ravi.cruddemo.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.ravi.cruddemo.entity.Employee;
import com.ravi.cruddemo.exception.EmployeeNotFoundException;
import com.ravi.cruddemo.repository.EmployeeRespository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    private EmployeeRespository employeeRespository;
    private ObjectMapper objectMapper;

    private static final Logger log = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    @Autowired
    public EmployeeServiceImpl(EmployeeRespository theEmployeeRepository, ObjectMapper theObjectMapper){
        employeeRespository = theEmployeeRepository;
        objectMapper = theObjectMapper;
    }

    /**
     * @return
     */
    @Override
    public List<Employee> findAll() {
        log.info("Going to findAll Employees");
        return employeeRespository.findAll();
    }

    /**
     * @param employeeId
     * @return
     */
    @Override
    public Employee findById(long employeeId) {
        log.info("Going to findEmployeeById for id: {}", employeeId);
        Optional<Employee> dbEmployee = employeeRespository.findById(employeeId);

        if (dbEmployee.isPresent()){
            return dbEmployee.get();
        }

        throw new EmployeeNotFoundException("Employee not found for id: " + employeeId);
    }

    /**
     * @param employee
     * @return
     */
    @Override
    public Employee save(Employee employee) {
        try {
            log.info("Going to save employee with details: {}", objectMapper.writeValueAsString(employee));
        } catch (Exception exp){
            log.error("Exception while printing Employee obj, exp: {}", exp.getMessage(), exp);
        }
        return employeeRespository.save(employee);
    }

    /**
     * @param employeeId
     * @return
     */
    @Override
    public void deleteById(long employeeId) {
        log.info("Going to delete employee for id: {}", employeeId);

        Optional<Employee> dbEmployee = employeeRespository.findById(employeeId);

        if (dbEmployee.isPresent()){
            employeeRespository.deleteById(employeeId);
        } else {
            throw new EmployeeNotFoundException("Employee not found for id: " + employeeId);
        }
    }

    /**
     * @param patchBody
     * @return
     */
    @Override
    public Employee patchUpdate(long employeeId, Map<String, Object> patchBody) {
        Optional<Employee> dbEmployee = employeeRespository.findById(employeeId);

        if (!dbEmployee.isPresent()){
            throw new EmployeeNotFoundException("Employee not found for id: " + employeeId);
        }

        if (patchBody.containsKey("id")){
            throw new RuntimeException("Patch request body can't contain 'id' field");
        }

        Employee toBeSaved = dbEmployee.get();
        toBeSaved = applyPatch(toBeSaved, patchBody);

        return employeeRespository.save(toBeSaved);
    }

    private Employee applyPatch(Employee employee, Map<String, Object> patchBody) {
        ObjectNode employeeNode = objectMapper.convertValue(employee, ObjectNode.class);
        ObjectNode patchNode = objectMapper.convertValue(patchBody, ObjectNode.class);

        employeeNode.setAll(patchNode);

        return objectMapper.convertValue(employeeNode, Employee.class);
    }

}
