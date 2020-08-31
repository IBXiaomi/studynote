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


    public Student(int id, String username) {
        this.id = id;
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
