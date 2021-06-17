package com.javadev.datasources.controller;

import com.javadev.datasources.entity.Course;
import com.javadev.datasources.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseController {

    private CourseRepository courseRepository;

    @Autowired
    public CourseController(CourseRepository courseRepository){
        this.courseRepository = courseRepository;
    }

    @GetMapping("/all")
    public List<Course> findAllCourses(){
        return courseRepository.findAllCourses();
    }
}
