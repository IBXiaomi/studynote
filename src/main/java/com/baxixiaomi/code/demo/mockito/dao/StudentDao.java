package com.baxixiaomi.code.demo.mockito.dao;

import com.baxixiaomi.code.demo.mockito.domain.Student;
import org.springframework.stereotype.Component;


public class StudentDao {


    public Student getStudentById(int id) {
        Student student = new Student(id,"jamie",10);
        return student;
    }
}
