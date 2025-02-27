package com.ravi.cruddemo.controller;

import com.ravi.cruddemo.entity.Employee;

import com.ravi.cruddemo.service.EmployeeService;
import org.hibernate.boot.jaxb.mapping.marshall.TemporalTypeMarshalling;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeController {

    private EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService theEmployeeService){
        this.employeeService = theEmployeeService;
    }

    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> findAll(){
        return new ResponseEntity<>(employeeService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/employees/{employeeId}")
    public ResponseEntity<Employee> findById(@PathVariable long employeeId){
        return new ResponseEntity<>(employeeService.findById(employeeId), HttpStatus.OK);
    }

    @PostMapping("/employees")
    public ResponseEntity<Employee> create(@RequestBody Employee employee){
        employee.setId(0);
        return new ResponseEntity<>(employeeService.save(employee), HttpStatus.CREATED);
    }

    @PutMapping("/employees")
    public  ResponseEntity<Employee> update(@RequestParam(name = "employee_id", required = true)long employeeId, @RequestBody Employee employee){
        return new ResponseEntity<>(employeeService.save(employee), HttpStatus.OK);
    }

    @DeleteMapping("/employees/{employeeId}")
    public ResponseEntity<String> deleteById(@PathVariable long employeeId){
        Employee dbEmployee = employeeService.findById(employeeId);

        employeeService.deleteById(employeeId);
        return new ResponseEntity<>("Deleted employee with id: " + employeeId, HttpStatus.OK);
    }

    @GetMapping("/employees/findByFirstName")
    public ResponseEntity<List<Employee>> findByFirstName(@RequestParam(name = "first_name", required = true) String firstName){
        return new ResponseEntity<>(employeeService.findByFirstName(firstName), HttpStatus.OK);
    }

    @GetMapping("/employees/findByLastName")
    public ResponseEntity<List<Employee>> findByLastName(@RequestParam(name = "last_name", required = true) String lastName){
        return new ResponseEntity<>(employeeService.findByLastName(lastName), HttpStatus.OK);
    }

    @GetMapping("/employees/findByEmail")
    public ResponseEntity<List<Employee>> findByEmail(@RequestParam(name = "email", required = true) String email) {
        return new ResponseEntity<>(employeeService.findByEmail(email), HttpStatus.OK);
    }
}
