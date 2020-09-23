package com.baxixiaomi.code.demo.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 实体类
 *
 * @author 吧嘻小米
 * @date 2020/09/23
 */
@Getter
@Setter
@ToString
public class Teacher {
    private String username;
    private String address;

    public Teacher(String username) {
        this.username = username;
    }
}
