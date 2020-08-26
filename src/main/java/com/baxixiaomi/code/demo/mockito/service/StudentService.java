package com.baxixiaomi.code.demo.mockito.service;

import com.baxixiaomi.code.demo.mockito.dao.StudentDao;
import com.baxixiaomi.code.demo.mockito.domain.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


public class StudentService {


     StudentDao studentDao;

    public StudentService(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    public String getStudent(int id) {
        Student studentById = studentDao.getStudentById(id);
        if (null == studentById) {
            return "failed";
        } else {
            return "success";
        }
    }
}
