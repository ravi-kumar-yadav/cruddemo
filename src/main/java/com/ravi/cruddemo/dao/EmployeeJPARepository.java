package com.ravi.cruddemo.dao;

import com.ravi.cruddemo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeJPARepository extends JpaRepository<Employee, Long> {

    @Query(name = "findAll")
    List<Employee> findAll();

    @Query(name = "findById", nativeQuery = true)
    Employee findById(@Param("id") long id);
}
