package com.baxixiaomi.code.demo.mockito.service;

import com.baxixiaomi.code.demo.mockito.domain.Student;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

@RunWith(MockitoJUnitRunner.class)
public class StudentServiceTest {

    @Autowired
    StudentService studentService;


    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void getStudent() {
        Student student = studentService.getStudent(1);
        Assert.assertNotNull(student);
        //Assert.assertEquals("jamie",student.getUsername());
    }
}