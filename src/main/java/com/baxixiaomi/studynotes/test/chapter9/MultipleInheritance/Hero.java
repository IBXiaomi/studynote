package com.baxixiaomi.studynotes.test.chapter9.MultipleInheritance;

/**
 * 超人
 *
 * @author 吧嘻小米
 * @date 2020/4/8
 */
public class Hero extends ActionCharacter implements CanFight, CanFly, CanSwim {

    @Override
    public void fly() {
        System.out.println("can fly");
    }

    @Override
    public void swim() {
        System.out.println("can swim");
    }
}
