package com.ravi.cruddemo.controller;

import com.ravi.cruddemo.entity.Employee;
import com.ravi.cruddemo.service.EmployeeService;
import com.ravi.cruddemo.service.EmployeeServiceImpl;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;

import java.net.URI;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class EmployeeController {
    private EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> findAll(){
        return ResponseEntity.ok(employeeService.findAll());
    }

    @GetMapping("/employees/{employeeId}")
    public ResponseEntity<Employee> findById(@PathVariable Long employeeId){
        return ResponseEntity.ok(employeeService.findById(employeeId));
    }

    @PostMapping("/employees")
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee){
        // to make a new db-entry for Employee
        employee.setId(0);
        Employee dbEmployee = employeeService.save(employee);

        URI location = URI.create("/employees/" + dbEmployee.getId());

        return ResponseEntity.created(location).body(dbEmployee);
    }

    @PutMapping("/employees")
    public ResponseEntity<Employee> updateEmployee (@RequestBody Employee employee){
        // check if id is present or not
        if (employee.getId() <= 0){
            throw new RuntimeException("Invalid employeeId: " + employee.getId() + ", it should be > 0");
        }

        return ResponseEntity.ok(employeeService.save(employee));
    }


    @PatchMapping("/employees/{employeeId}")
    public ResponseEntity<Employee> patchUpdateEmployee(@PathVariable Long employeeId, @RequestBody Map<String, Object> patchBody){
        if (patchBody.containsKey("id")){
            throw new RuntimeException("Patch request body cannot contain 'id'");
        }

        return ResponseEntity.ok(employeeService.patchUpdate(employeeId, patchBody));
    }

    @DeleteMapping("/employees/{employeeId}")
    public ResponseEntity<String> deleteById(@PathVariable Long employeeId){
        employeeService.deleteById(employeeId);
        return ResponseEntity.ok("Deleted employee with id: " + employeeId);
    }

    @GetMapping("/employees/findByFirstName")
    public ResponseEntity<List<Employee>> findByFirstName(@RequestParam(name = "first_name", required = true) String firstName){
        return ResponseEntity.ok(employeeService.findByFirstName(firstName));
    }

    @GetMapping("/employees/findByLastName")
    public ResponseEntity<List<Employee>> findByLastName(@RequestParam(value = "last_name", required = true) String lastName){
        return ResponseEntity.ok(employeeService.findByLastName(lastName));
    }

    @GetMapping("/employees/findByEmail")
    public ResponseEntity<List<Employee>> findByEmail(@RequestParam(name = "email", required = true) String email){
        return ResponseEntity.ok(employeeService.findByEmail(email));
    }

}
