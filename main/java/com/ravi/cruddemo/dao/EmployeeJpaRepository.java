package com.ravi.cruddemo.dao;

import com.ravi.cruddemo.entity.Employee;
import jakarta.annotation.PreDestroy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeJpaRepository extends JpaRepository<Employee, Long> {

    @Query(name = "findByFirstName")
    public List<Employee> findByFirstName(@Param("firstName") String firstName);

    @Query(name = "findByLastNameNative", nativeQuery = true)
    public List<Employee> findByLastName(@Param("lastName") String lastName);

    @Query(value = "select * from employee where email = :email", nativeQuery = true)
    public List<Employee> findByEmail(@Param("email") String email);
}
