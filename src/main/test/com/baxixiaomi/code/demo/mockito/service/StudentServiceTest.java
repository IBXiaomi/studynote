package com.baxixiaomi.code.demo.mockito.service;

import com.baxixiaomi.code.demo.mockito.dao.StudentDao;
import com.baxixiaomi.code.demo.mockito.domain.Student;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class StudentServiceTest {

    @Mock
    private StudentDao studentDao;

    private StudentService studentService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.studentService = new StudentService(studentDao);
    }

    @Test
    public void getStudent() {
        Student student = new Student(1, "jamie");
        when(studentDao.fetchStudent(1)).thenReturn(student);
        boolean jamie = studentService.getStudent(1, "jamie");
        assertTrue(jamie);
    }
}