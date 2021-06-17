package com.javadev.datasources.repository;

import com.javadev.datasources.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class StudentRepository {


    private final SessionFactory sessionFactory;

    @Autowired
    public StudentRepository(@Qualifier("studentSessionFactory") SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Transactional("studentTransactionManager")
    public List<Student> findAllStudents(){
        Session studentsSession = sessionFactory.getCurrentSession();

        Query<Student> studentsQuery = studentsSession.createQuery("from Student",Student.class);

        List<Student> students = studentsQuery.getResultList();

        return students;
    }
}
