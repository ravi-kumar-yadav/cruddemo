package com.ravi.cruddemo.repository;

import com.ravi.cruddemo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query(value = "select e from Employee e where e.firstName = :firstName", nativeQuery = false)
    List<Employee> fetchByFirstName(String firstName);

    @Query(value = "select * from employee where last_name = :lastName", nativeQuery = true)
    List<Employee> fetchByLastName(String lastName);

    @Query(name = "Employee.fetchByEmail")
    List<Employee> fetchByEmail(String email);

    @Query(name = "Employee.fetchByEmailAndFirstName.Native")
    List<Employee> fetchByEmailAndFirstName(String email, String firstName);
}
