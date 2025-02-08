package com.ravi.cruddemo.controller;

import com.ravi.cruddemo.entity.Employee;
import com.ravi.cruddemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService theEmployeeService){
        employeeService = theEmployeeService;
    }

    @GetMapping("/")
    public ResponseEntity<List<Employee>> findAll(){
        return new ResponseEntity<>(employeeService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<Employee> findById(@PathVariable long employeeId){
        return new ResponseEntity<>(employeeService.findById(employeeId), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Employee> save(@RequestBody Employee employee){
        employee.setId(0);
        return new ResponseEntity<>(employeeService.save(employee), HttpStatus.CREATED);
    }

    @PutMapping("/")
    public ResponseEntity<Employee> update(@RequestBody Employee employee){
        return new ResponseEntity<>(employeeService.save(employee), HttpStatus.OK);
    }

    @DeleteMapping("/{employeeId}")
    public ResponseEntity<String> deleteById(@PathVariable long employeeId){
        employeeService.deleteById(employeeId);
        return new ResponseEntity<>("Deleted employee with id: " + employeeId, HttpStatus.OK);
    }

    @GetMapping("/findByFirstName")
    public ResponseEntity<List<Employee>> findByFirstName(@RequestParam(value = "first_name", required = true) String firstName){
        return new ResponseEntity<>(employeeService.findByFirstName(firstName), HttpStatus.OK);
    }

    @GetMapping("/findByLastName")
    public ResponseEntity<List<Employee>> findByLastName(@RequestParam(value = "last_name", required = true) String lastName){
        return new ResponseEntity<>(employeeService.findByLastName(lastName), HttpStatus.OK);
    }

    @GetMapping("/findByEmail")
    public ResponseEntity<List<Employee>> findByEmail(@RequestParam(value = "email", required = true) String email){
        return new ResponseEntity<>(employeeService.findByEmail(email),HttpStatus.OK);
    }
}
