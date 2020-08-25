package com.baxixiaomi.code.demo.mockito.domain;

/**
 * 练习mockito
 *
 * @author 吧嘻小米
 * @date 2020/08/25
 */
public class Student {
    private int id;

    private String username;

    private int age;

    public Student(int id, String username, int age) {
        this.id = id;
        this.username = username;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
