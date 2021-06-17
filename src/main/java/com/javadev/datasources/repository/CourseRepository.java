package com.javadev.datasources.repository;

import com.javadev.datasources.entity.Course;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class CourseRepository {

    private final SessionFactory sessionFactory;

    @Autowired
    public CourseRepository(@Qualifier("courseSessionFactory") SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional("courseTransactionManager")
    public List<Course> findAllCourses(){
        Session courseSession = sessionFactory.getCurrentSession();

        Query<Course> courseQuery = courseSession.createQuery("from Course",Course.class);

        List<Course> courses = courseQuery.getResultList();

        return courses;
    }
}
