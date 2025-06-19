package com.ravi.cruddemo.dao;

import com.ravi.cruddemo.entity.Student;

import java.util.List;

public interface StudentDAO {

    void save(Student student);

    Student findById(long id);

    List<Student> findAll();

    List<Student> findByFirstName(String firstName);

    void update(Student student);

    void delete(long id);

    void deleteAll();
}
