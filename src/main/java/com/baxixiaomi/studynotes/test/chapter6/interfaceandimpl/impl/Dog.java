package com.baxixiaomi.studynotes.test.chapter6.interfaceandimpl.impl;

import com.baxixiaomi.studynotes.test.chapter6.interfaceandimpl.Animal;

/**
 * 实现了动物的技能,并将权限设置为包权限访问
 *
 * @author 吧嘻小米
 * @since 1.0-SNAPSHOT
 */
class Dog implements Animal {

    @Override
    public Integer getSum(int a, int b) {
        return dogGetNum(a, b);
    }

    /**
     * 具体的实现,方法的设计者可以任意的修改方法的实现,不会对外产生影响
     * 修饰符为private
     *
     * @param a 参数a
     * @param b 参数b
     * @return 返回结果
     */
    private Integer dogGetNum(int a, int b) {
        return a + b;
    }
}
