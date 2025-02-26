package com.ravi.cruddemo.controller;

import com.ravi.cruddemo.entity.Employee;
import com.ravi.cruddemo.service.EmployeeService;
import com.ravi.cruddemo.service.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeController {

    EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService theEmployeeService){
        this.employeeService = theEmployeeService;
    }

    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> fetchAll(){
        return new ResponseEntity<>(employeeService.fetchAll(), HttpStatus.OK);
    }

    @GetMapping("/employees/{employeeId}")
    public ResponseEntity<Employee> fetchById(@PathVariable long employeeId){
        return new ResponseEntity<>(employeeService.fetchById(employeeId), HttpStatus.OK);
    }

    @PostMapping("/employees")
    public ResponseEntity<Employee> save(@RequestBody Employee employee){
        employee.setId(0);
        return new ResponseEntity<>(employeeService.save(employee), HttpStatus.CREATED);
    }

    @PutMapping("/employees")
    public ResponseEntity<Employee> update(@RequestParam long employeeId, @RequestBody Employee employee){
        employee.setId(employeeId);

        return new ResponseEntity<>(employeeService.save(employee), HttpStatus.OK);
    }

    @DeleteMapping("/employees/{employeeId}")
    public ResponseEntity<String> deleteById(@PathVariable long employeeId){
        employeeService.deleteById(employeeId);

        return new ResponseEntity<>("Successfully deleted employee with id: " + employeeId, HttpStatus.OK);
    }


    @GetMapping("/employees/findByFirstName")
    public ResponseEntity<List<Employee>> findByFirstName(@RequestParam(required = true, value="first_name") String firstName){
        return new ResponseEntity<>(employeeService.findByFirstName(firstName), HttpStatus.OK);
    }

    @GetMapping("/employees/findByLastName")
    public ResponseEntity<List<Employee>> findByLastName(@RequestParam(required = true, value="last_name") String lastName){
        return new ResponseEntity<>(employeeService.findByLastName(lastName), HttpStatus.OK);
    }

    @GetMapping("/employees/findByEmail")
    public ResponseEntity<List<Employee>> findByEmail(@RequestParam(required = true, value = "email") String email){
        return new ResponseEntity<>(employeeService.findByEmail(email), HttpStatus.OK);
    }
}
