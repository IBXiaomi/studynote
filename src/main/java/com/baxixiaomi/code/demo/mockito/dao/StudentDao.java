package com.baxixiaomi.code.demo.mockito.dao;


import com.baxixiaomi.code.demo.mockito.domain.Student;

public interface StudentDao {

    Student fetchStudent(int id);

    boolean updateStudent(Student student);
}
