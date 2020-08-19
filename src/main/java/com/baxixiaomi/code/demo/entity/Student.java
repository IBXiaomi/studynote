package com.baxixiaomi.code.demo.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * student实体类
 *
 * @author jamiewang
 * @date 2020/08/19
 */
@Data
@Getter
@Setter
public class Student {
    private String username;
    private Date birthday;


}
