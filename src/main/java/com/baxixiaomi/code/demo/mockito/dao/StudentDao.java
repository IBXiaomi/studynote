package com.baxixiaomi.code.demo.mockito.dao;

import com.baxixiaomi.code.demo.mockito.domain.Student;

public class StudentDao {

    public Student getStudentById() {
        return new Student(1, "jamie", 10);
    }
}
