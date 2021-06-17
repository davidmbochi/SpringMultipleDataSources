package com.javadev.datasources.controller;

import com.javadev.datasources.entity.Student;
import com.javadev.datasources.repository.StudentRepository;
import jdk.dynalink.linker.LinkerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    private StudentRepository studentRepository;

    @Autowired
    public StudentController(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    @GetMapping("/all")
    public List<Student> findAllStudents(){
        return studentRepository.findAllStudents();
    }
}
