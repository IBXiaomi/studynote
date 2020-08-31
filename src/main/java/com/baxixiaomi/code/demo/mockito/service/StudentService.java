package com.baxixiaomi.code.demo.mockito.service;

import com.baxixiaomi.code.demo.mockito.dao.StudentDao;
import com.baxixiaomi.code.demo.mockito.domain.Student;

public class StudentService {

    private final StudentDao studentDao;

    public StudentService(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    public boolean getStudent(int id, String name) {
        Student fetchStudent = studentDao.fetchStudent(id);
        if (null != fetchStudent) {
            Student student = new Student(fetchStudent.getId(), name);
            studentDao.updateStudent(student);
            return true;
        } else {
            return false;
        }
    }

}
