package com.ravi.cruddemo.controller;

import com.ravi.cruddemo.entity.Employee;
import com.ravi.cruddemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class EmployeeController {

    private EmployeeService employeeService;

    public EmployeeController(){
    }

    @Autowired
    public EmployeeController(EmployeeService theEmployeeService){
        employeeService = theEmployeeService;
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
    public ResponseEntity<Employee> save(@RequestBody Employee employee){
        employee.setId(0);
        return new ResponseEntity<>(employeeService.save(employee), HttpStatus.CREATED);
    }

    @PutMapping("/employees")
    public ResponseEntity<Employee> update(@RequestBody Employee employee){
        return new ResponseEntity<>(employeeService.save(employee), HttpStatus.OK);
    }

    @PatchMapping("/employees/{employeeId}")
    public ResponseEntity<Employee> patch(@PathVariable long employeeId, Map<String, Object> patchBody){
        return new ResponseEntity<>(employeeService.patchUpdate(employeeId, patchBody), HttpStatus.OK);
    }
}
