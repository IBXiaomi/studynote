package com.baxixiaomi.code.demo.mockito.service;

import com.baxixiaomi.code.demo.mockito.dao.StudentDao;
import com.baxixiaomi.code.demo.mockito.domain.Student;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class StudentServiceTest {

    private StudentService studentService;

    private StudentDao studentDao;


    @Before
    public void setUp() throws Exception {
        this.studentDao = Mockito.mock(StudentDao.class);
        this.studentService = new StudentService(studentDao);
    }

    @Test
    public void getStudent() {
        //Assert.assertNotNull(student);
        Mockito.when(studentService.getStudent(1)).thenReturn("success");
        //Assert.assertEquals("success",studentService.getStudent(1));
    }
}