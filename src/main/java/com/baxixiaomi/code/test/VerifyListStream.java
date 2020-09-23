package com.baxixiaomi.code.test;

import com.baxixiaomi.code.demo.entity.Student;
import com.baxixiaomi.code.demo.entity.Teacher;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 验证list流
 *
 * @author 吧嘻小米
 * @date 2020/09/23
 */
public class VerifyListStream {
    private static List<Teacher> response = new ArrayList<Teacher>(6);
    private static List<Teacher> request1 = new ArrayList<>(3);
    private static List<Teacher> request2 = new ArrayList<>(3);


    public static void main(String[] args) {
        Teacher teacher1 = new Teacher("jamie1");
        Teacher teacher2 = new Teacher("jamie2");
        Teacher teacher3 = new Teacher("jamie3");
        Teacher teacher4 = new Teacher("jamie4");
        Teacher teacher5 = new Teacher("jamie5");
        Teacher teacher6 = new Teacher("jamie6");
        request1.add(teacher1);
        request1.add(teacher2);
        request1.add(teacher3);
        request2.add(teacher4);
        request2.add(teacher5);
        request2.add(teacher6);
        response.addAll(request1.stream().
                map(student -> {
                    Teacher teacher = new Teacher("wjw1");
                    if (null != student.getUsername()) {
                        teacher.setAddress(student.getUsername());
                    }
                    return teacher;
                })
                .collect(Collectors.toList()));
        response.addAll(request2.stream().
                map(student -> {
                    Teacher teacher = new Teacher("wjw2");
                    if (null != student.getUsername()) {
                        teacher.setUsername(student.getUsername());
                    }
                    return teacher;
                })
                .collect(Collectors.toList()));
        response.stream().forEach(System.out::println);
       // response.stream().filter(teacher -> null != teacher.getAddress()).forEach(System.out.println(t););
    }
}
