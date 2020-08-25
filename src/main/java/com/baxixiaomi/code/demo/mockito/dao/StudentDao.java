package com.baxixiaomi.code.demo.mockito.dao;

import com.baxixiaomi.code.demo.mockito.domain.Student;
import org.springframework.stereotype.Component;

@Component
public class StudentDao {

    public Student getStudentById(int id) {
        return new Student(1, "jamie", 10);
    }
}
