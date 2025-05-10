package com.ravi.cruddemo.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ravi.cruddemo.entity.Employee;
import com.ravi.cruddemo.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeController {

    private EmployeeService employeeService;
    private ObjectMapper objectMapper;
    private static final Logger log = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    public EmployeeController(EmployeeService employeeService, ObjectMapper objectMapper){
        this.employeeService = employeeService;
        this.objectMapper = objectMapper;
    }

    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> getAllEmployees() throws JsonProcessingException {
        log.info("In controller: getAllEmployees");
        return new ResponseEntity<>(employeeService.getAllEmployees(), HttpStatus.OK);
    }

    @GetMapping("/employees/{employeeId}")
    public ResponseEntity<Employee> getById(@PathVariable long employeeId) throws JsonProcessingException {
        log.info("In controller: getById, employeeId: {}", employeeId);
        return new ResponseEntity<>(employeeService.getById(employeeId), HttpStatus.OK);
    }


    @PostMapping("/employees")
    public ResponseEntity<Employee> save(@RequestBody Employee employee) throws JsonProcessingException {
        log.info("In controller: save, employee: {}", objectMapper.writeValueAsString(employee));
        employee.setId(0);
        return new ResponseEntity<>(employeeService.save(employee), HttpStatus.CREATED);
    }


    @PutMapping("/employees")
    public ResponseEntity<Employee> update(@RequestBody Employee employee) throws JsonProcessingException {
        log.info("In controller: update, employee: {}", objectMapper.writeValueAsString(employee));
        if (employee.getId() == 0){
            throw new RuntimeException("Missing employeeId in PUT for updating Employee!");
        }
        return new ResponseEntity<>(employeeService.save(employee), HttpStatus.CREATED);
    }


    @DeleteMapping("/employees/{employeeId}")
    public ResponseEntity<String> deleteById(@PathVariable long employeeId) throws JsonProcessingException {
        log.info("In controller: deleteById, employeeId: {}", employeeId);
        employeeService.deleteById(employeeId);
        return new ResponseEntity<>("Employee deleted succussefully!!! employeeId: " + employeeId, HttpStatus.OK);
    }

    @GetMapping("/employees/findByFirstName")
    public ResponseEntity<List<Employee>> fetchByFirstName(@RequestParam(name = "first_name", required = true) String firstName){
        log.info("fetching all employees with first_name: {}", firstName);
        return new ResponseEntity<>(employeeService.fetchByFirstName(firstName), HttpStatus.OK);
    }

    @GetMapping("/employees/findByLastName")
    public ResponseEntity<List<Employee>> fetchByLastName(@RequestParam(name = "last_name", required = true) String lastName){
        log.info("fetching all employees with last_name: {}", lastName);
        return new ResponseEntity<>(employeeService.fetchByLastName(lastName), HttpStatus.OK);
    }



    @GetMapping("/employees/findByEmail")
    public ResponseEntity<List<Employee>> fetchByEmail(@RequestParam (name = "email", required = true) String email){
        log.info("fetching all employees with email: {}", email);
        return new ResponseEntity<>(employeeService.fetchByEmail(email), HttpStatus.OK);
    }

    @GetMapping("/employees/findByEmailAndFirstName")
    public ResponseEntity<List<Employee>> fetchByEmailAndFirstName(@RequestParam (name = "email", required = true) String email, @RequestParam (name = "first_name", required = true) String firstName){
        log.info("fetching all employees with email: {} and first_name: {}", email, firstName);
        return new ResponseEntity<>(employeeService.fetchByEmailAndFirstName(email, firstName), HttpStatus.OK);
    }
}
