package com.baxixiaomi.code.demo.mockito.service;

import com.baxixiaomi.code.demo.mockito.dao.StudentDao;
import com.baxixiaomi.code.demo.mockito.domain.Student;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class StudentServiceTest {

    private StudentDao studentDao;

    private StudentService studentService;

    @Before
    public void setUp() throws Exception {
        this.studentDao = Mockito.mock(StudentDao.class);
        this.studentService = new StudentService(studentDao);
    }

    @Test
    public void getStudent() {
        Student student = new Student();
        when(studentService.getStudent()).thenReturn(student);

        assertNotNull(student);
    }
}