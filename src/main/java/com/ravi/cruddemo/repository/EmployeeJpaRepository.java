package com.ravi.cruddemo.repository;

import com.ravi.cruddemo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeJpaRepository extends JpaRepository<Employee, Long> {

    @Query(name = "Employee.findByFirstName")
    List<Employee> findByFirstName(String firstName);

    @Query(value = "select e from Employee e where e.lastName = :lastName")
    List<Employee> findByLastName(String lastName);

    @Query(name = "Employee.findByEmail", nativeQuery = true)
    List<Employee> findByEmail(String email);
}
