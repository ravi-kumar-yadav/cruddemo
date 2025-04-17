package com.ravi.cruddemo.dao;

import com.ravi.cruddemo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeJPARepository extends JpaRepository<Employee, Long> {

    @Query(name = "findByFirstName")
    List<Employee> findByFirstName(@Param("firstName") String firstName);

    @Query(name = "findByLastName")
    List<Employee> findByLastName(@Param("lastName") String lastName);

    @Query(name = "findByEmailNative", nativeQuery = true)
    List<Employee> findByEmail(@Param("email") String email);

    @Query(name = "findByFirstNameAndEmailNative", nativeQuery = true)
    List<Employee> findByFirstNameAndEmail(@Param("firstName") String firstName, @Param("email") String email);
}
