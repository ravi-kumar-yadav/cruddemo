package com.ravi.cruddemo.dao;

import com.ravi.cruddemo.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDAOImpl implements  EmployeeDAO{
    private EntityManager entityManager;

    @Autowired
    public EmployeeDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Employee> findAll() {
        TypedQuery<Employee> query = entityManager.createQuery("from Employee", Employee.class);
        return query.getResultList();
    }

    @Override
    public Employee findById(long employeeId) {
        return entityManager.find(Employee.class, employeeId);
    }

    @Override
    public Employee save(Employee employee) {
        return entityManager.merge(employee);
    }

    @Override
    public void deleteById(long employeeId) {
        Employee dbEmployee = entityManager.find(Employee.class, employeeId);
        entityManager.remove(dbEmployee);
        return;
    }

    @Override
    public List<Employee> findByFirstName(String firstName) {
        TypedQuery<Employee> query = entityManager.createNamedQuery("findByFirstName", Employee.class);
        query.setParameter("firstName", firstName);
        return query.getResultList();
    }

    @Override
    public List<Employee> findByLastName(String lastName) {
        TypedQuery<Employee> query = entityManager.createNamedQuery("findByLastName", Employee.class);
        query.setParameter("lastName", lastName);
        return query.getResultList();
    }

    @Override
    public List<Employee> findByEmail(String email) {
        Query query = entityManager.createNativeQuery("select * from employee where email = :email", Employee.class);
        query.setParameter("email", email);
        List<Employee> employees = query.getResultList();
        return employees;
    }
}
