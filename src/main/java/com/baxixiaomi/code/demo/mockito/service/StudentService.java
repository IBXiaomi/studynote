package com.baxixiaomi.code.demo.mockito.service;

import com.baxixiaomi.code.demo.mockito.dao.StudentDao;
import com.baxixiaomi.code.demo.mockito.domain.Student;

public class StudentService {

    private final StudentDao studentDao;

    public StudentService(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    public Student getStudent() {
        return studentDao.getStudentById();
    }

}
