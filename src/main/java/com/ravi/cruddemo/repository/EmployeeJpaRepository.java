package com.ravi.cruddemo.repository;

import com.ravi.cruddemo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeJpaRepository extends JpaRepository<Employee, Long> {

    @Override
    @Query(value = "select e from Employee e")
    List<Employee> findAll();

    @Query(value = "Select * from employee where first_name = :firstName", nativeQuery = true)
    List<Employee> findByFirstName(String firstName);

    @Query(name = "findByLastNameNative", nativeQuery = true)
    List<Employee> findByLastName(String lastName);

    @Query(name = "findByEmail", nativeQuery = false)
    List<Employee> findByEmail(String email);
}
