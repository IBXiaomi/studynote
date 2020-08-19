package com.baxixiaomi.code.demo.stream.filter;

import com.baxixiaomi.code.demo.entity.Student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 练习关于java8新特性中的stream流的filter
 *
 * @author jamiewang
 * @date 2020/08/19
 */
public class NewFeatureAboutStream {
    public static void main(String[] args) {
        test01();
        //test02();
    }

    private static void test01() {
        Student student1 = new Student();
        Student student2 = new Student();
        student1.setUsername("1");
        student2.setUsername("2");
        List<String> list = new ArrayList<String>(5);
        List<Student> list1 = new ArrayList<Student>(2);
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list1.add(student1);
        list1.add(student2);
        String string = "1";
        list.stream().filter(str -> str.equals(string)).forEach(System.out::println);
        list1.stream().collect(Collectors.toMap(Function.identity(), Student::getUsername))
                .entrySet().stream().forEach(System.out::println);

    }

    private static void test02() {
        Map<String, String> hashMap = new HashMap<String, String>(5);
        hashMap.put("1", "2");
        hashMap.put("3", "4");
        hashMap.put("5", "6");
        hashMap.put("7", "8");
        hashMap.put("9", "10");
        hashMap.entrySet().stream().filter(str -> str.getValue().equals("11")).forEach(System.out::println);
    }


}
