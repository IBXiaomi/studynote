package com.baxixiaomi.studynotes.test.chapter6;

/**
 * 全访问权限
 *
 * @author 吧嘻小米
 * @since 1.0-SNAPSHOT
 */
public class Soup1 {
    private Soup1() {
    }

    public static Soup1 makeSoup1() {
        return new Soup1();
    }

}
