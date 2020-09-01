package com.baxixiaomi.code.demo.mockito.service;

import com.baxixiaomi.code.demo.mockito.dao.StudentDao;
import com.baxixiaomi.code.demo.mockito.domain.Student;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

/**
 * 通过构造方法实现单元测试
 *
 * @author wjw
 * @date 2020/09/01
 */
public class StudentServiceByMockTest {

    private StudentDao studentDao;

    private StudentService studentService;

    @Before
    public void init() {
        this.studentDao = Mockito.mock(StudentDao.class);
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
