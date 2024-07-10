package com.jcarloshg.hibernate.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jcarloshg.hibernate.entities.Student;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

@Repository()
public class StudentMySQL implements StudentDAO {

    private EntityManager entityManager;

    @Autowired
    public StudentMySQL(EntityManager entityManager) {
        System.out.println("The [StudentMySQL] was created. :)");
        this.entityManager = entityManager;
    }

    // ============================================================
    // CREATE
    // ============================================================

    @Override
    @Transactional
    public void save(Student student) {
        this.entityManager.persist(student);
    }

    // ============================================================
    // READ
    // ============================================================

    @Override
    public Student findByID(Integer id) {
        Student studentFounded = this.entityManager.find(Student.class, id);
        return studentFounded;
    }

    @Override
    public List<Student> findAll() {
        // ! the name of the column must be the name from the entity NOT from DataBase
        TypedQuery<Student> query = entityManager.createQuery("FROM Student order by last_name desc", Student.class);
        return query.getResultList();
    }

    @Override
    public List<Student> findByLastName(String lastName) {
        TypedQuery<Student> query = entityManager
                .createQuery(
                        "FROM Student WHERE last_name=:lastName",
                        Student.class);
        query.setParameter("lastName", lastName);
        return query.getResultList();
    }

    @Override
    @Transactional
    public void update(Student student) {
        entityManager.merge(student);
    }

}
