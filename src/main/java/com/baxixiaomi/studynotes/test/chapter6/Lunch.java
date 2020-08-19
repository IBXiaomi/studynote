package com.baxixiaomi.studynotes.test.chapter6;

/**
 * 展示不同的访问权限，如何创建对象
 *
 * @author 吧嘻小米
 * @since 1.0-SNAPSHOT
 */
public class Lunch {
    private static void testStatic() {
        Soup1 soup1 = Soup1.makeSoup1();
        System.out.println(soup1);
    }

    private static void singleton() {
        Soup2.makeSoup2().f();
    }

    public static void main(String[] args) {
        testStatic();
        singleton();
    }
}
