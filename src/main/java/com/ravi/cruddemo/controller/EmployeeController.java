package com.ravi.cruddemo.controller;

import com.ravi.cruddemo.entity.Employee;
import com.ravi.cruddemo.service.EmployeeService;
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
        employeeService = theEmployeeService;
    }

    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> findAll(){
        return new ResponseEntity<>(employeeService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/employees/{employeeId}")
    public ResponseEntity<Employee> findById(@PathVariable Long employeeId){
        return new ResponseEntity<>(employeeService.findById(employeeId), HttpStatus.OK);
    }

    @PostMapping("/employees")
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee){
        employee.setId(0);
        return new ResponseEntity<>(employeeService.save(employee), HttpStatus.CREATED);
    }

    @PutMapping("/employees")
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee){
        return new ResponseEntity<>(employeeService.save(employee), HttpStatus.OK);
    }

    @DeleteMapping("/employees/{employeeId}")
    public ResponseEntity<String> deleteById(@PathVariable Long employeeId){
        employeeService.deleteById(employeeId);
        return new ResponseEntity<>("Employee deleted for id: " + employeeId, HttpStatus.OK);
    }

    @GetMapping("/employees/findByFirstName")
    public ResponseEntity<List<Employee>> findByFirstName(@RequestParam(value = "first_name", required = true) String firstName){
        return new ResponseEntity<>(employeeService.findByFirstName(firstName), HttpStatus.OK);
    }

    @GetMapping("/employees/findByLastName")
    public ResponseEntity<List<Employee>> findByLastName(@RequestParam(value = "last_name", required = true) String lastName){
        return new ResponseEntity<>(employeeService.findByLastName(lastName), HttpStatus.OK);
    }

    @GetMapping("/employees/findByEmail")
    public ResponseEntity<List<Employee>> findByEmail(@RequestParam(value = "email", required = true) String email){
        return new ResponseEntity<>(employeeService.findByEmail(email), HttpStatus.OK);
    }
}
