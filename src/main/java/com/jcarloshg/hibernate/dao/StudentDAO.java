package com.jcarloshg.hibernate.dao;

import java.util.List;

import com.jcarloshg.hibernate.entities.Student;

public interface StudentDAO {

    // Create
    void save(Student student);

    // Read
    Student findByID(Integer id);

    List<Student> findAll();

    List<Student> findByLastName(String lastName);

    // Update
    void update(Student student);

    // Delete
    void deleteByID(Integer id);

    int deleteAll();

}
