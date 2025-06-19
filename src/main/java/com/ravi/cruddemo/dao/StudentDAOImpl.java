package com.ravi.cruddemo.dao;

import com.ravi.cruddemo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class StudentDAOImpl implements StudentDAO{

    private EntityManager entityManager;

    @Autowired
    public StudentDAOImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    /**
     * @param student
     */
    @Override
    @Transactional
    public void save(Student student) {
        entityManager.persist(student);
    }

    @Override
    public Student findById(long id) {
        return entityManager.find(Student.class, id);
    }

    @Override
    public List<Student> findAll(){
        TypedQuery<Student> query = entityManager.createQuery("from Student", Student.class);

        return query.getResultList();
    }

    @Override
    public List<Student> findByFirstName(String firstName){
        TypedQuery<Student> query = entityManager.createQuery(("select s from Student s where s.firstName = :firstName"), Student.class);
        query.setParameter("firstName", firstName);
        return query.getResultList();
    }

    @Override
    @Transactional
    public void update(Student student){
        entityManager.merge(student);
    }

    @Override
    @Transactional
    public void delete(long id){
        Student student = entityManager.find(Student.class, id);

        System.out.println("Student details: " + student);
        entityManager.remove(student);

        System.out.println("Deleted student: with id: " + student);
    }


    @Override
    @Transactional
    public void deleteAll(){
        int rowsDeleted = entityManager.createQuery("delete from Student").executeUpdate();

        System.out.println("Total students removed: " + rowsDeleted);
    }
}
