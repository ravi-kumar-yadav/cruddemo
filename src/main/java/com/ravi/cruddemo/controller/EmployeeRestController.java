package com.ravi.cruddemo.controller;

import com.ravi.cruddemo.entity.Employee;
import com.ravi.cruddemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/api")
public class EmployeeRestController {

    private EmployeeService employeeService;

    @Autowired
    public EmployeeRestController(EmployeeService theEmployeeService){
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
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee){
        employee.setId(0);
        return new ResponseEntity<>(employeeService.save(employee), HttpStatus.CREATED);
    }

    @PutMapping("/employees")
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee){
        return new ResponseEntity<>(employeeService.save(employee), HttpStatus.OK);
    }

    @DeleteMapping("/employees/{employeeId}")
    public ResponseEntity<String> deleteEmployee(@PathVariable long employeeId){
        employeeService.deleteById(employeeId);

        return new ResponseEntity<>("Employee deleted successfully for id: " + employeeId, HttpStatus.OK);
    }

    // ***********************************
    // Generic Methods for complex queries
    // ***********************************

    @GetMapping("/employees/findByFirstName/{firstName}")
    public ResponseEntity<List<Employee>> findByFirstName (@PathVariable String firstName){
        return new ResponseEntity<>(employeeService.findByFirstName(firstName), HttpStatus.OK);
    }

    @GetMapping("/employees/findByLastName/{lastName}")
    public ResponseEntity<List<Employee>> findByLastName (@PathVariable String lastName){
        return new ResponseEntity<>(employeeService.findByLastName(lastName), HttpStatus.OK);
    }

    @GetMapping("/employees/findByEmail/{email}")
    public ResponseEntity<List<Employee>> findByEmail (@PathVariable String email){
        return new ResponseEntity<>(employeeService.findByEmail(email), HttpStatus.OK);
    }

    @GetMapping("/employees/findByFirstNameAndEmail")
    public ResponseEntity<List<Employee>> findByFirstNameAndEmail (@RequestParam(value = "first_name", required = true) String firstName, @RequestParam(value = "email", required = true) String email){
        return new ResponseEntity<>(employeeService.findByFirstNameAndEmail(firstName, email), HttpStatus.OK);
    }

    @GetMapping("/employees/findByLastNameAndEmail")
    public ResponseEntity<List<Employee>> findByLastNameAndEmail (@RequestParam(value = "last_name", required = true) String lastName, @RequestParam(value = "email", required = true) String email){
        return new ResponseEntity<>(employeeService.findByLastNameAndEmail(lastName, email), HttpStatus.OK);
    }

    @PatchMapping("/employees/{employeeId}")
    public ResponseEntity<Employee> patchUpdate(@PathVariable long employeeId, @RequestBody Map<String, Object> patchBody){
        return new ResponseEntity<>(employeeService.patchUpdate(employeeId, patchBody), HttpStatus.OK);
    }

}
