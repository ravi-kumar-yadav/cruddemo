package com.ravi.cruddemo.dao;

import com.ravi.cruddemo.entity.Employee;
import com.ravi.cruddemo.exception.EmployeeNotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO{

    EntityManager entityManager;

    @Autowired
    public EmployeeDAOImpl(EntityManager theEntityManager){
        this.entityManager = theEntityManager;
    }

    /**
     * @return
     */
    @Override
    public List<Employee> fetchAll() {
        TypedQuery<Employee> query = entityManager.createQuery("from Employee", Employee.class);
        return query.getResultList();
    }

    /**
     * @param employeeId
     * @return
     */
    @Override
    public Employee fetchById(long employeeId) {
        return entityManager.find(Employee.class, employeeId);
    }

    /**
     * @param employee
     * @return
     */
    @Override
    public Employee save(Employee employee) {
        Employee dbEmployee = entityManager.merge(employee);
        return dbEmployee;
    }

    /**
     * @param employeeId
     */
    @Override
    public void deleteById(long employeeId) {
        Employee dbEmployee = entityManager.find(Employee.class, employeeId);

        if (dbEmployee == null){
            throw new EmployeeNotFoundException("Employee not found for id: " + employeeId);
        }

        entityManager.remove(dbEmployee);
    }

    /**
     * @param firstName
     * @return
     */
    @Override
    public List<Employee> findByFirstName(String firstName) {
        Query theQuery = entityManager.createNamedQuery("Employee.findByFirstName", Employee.class);
        theQuery.setParameter("firstName", firstName);
        return theQuery.getResultList();
    }

    /**
     * @param lastName
     * @return
     */
    @Override
    public List<Employee> findByLastName(String lastName) {
        Query theQuery = entityManager.createNamedQuery("Employee.findByLastNameNative", Employee.class);
        theQuery.setParameter("lastName", lastName);
        return theQuery.getResultList();
    }

    /**
     * @param email
     * @return
     */
    @Override
    public List<Employee> findByEmail(String email) {
//        TypedQuery<Employee> theQuery = entityManager.createQuery("select e from Employee e where e.email = :email", Employee.class);
        Query theQuery = entityManager.createNativeQuery("select * from Employee e where e.email = :email", Employee.class);
        theQuery.setParameter("email", email);
        return theQuery.getResultList();
    }
}
