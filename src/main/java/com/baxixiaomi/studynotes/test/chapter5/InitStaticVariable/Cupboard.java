package com.baxixiaomi.studynotes.test.chapter5.InitStaticVariable;

/**
 * 定义一个cupboard类
 *
 * @author 吧嘻小米
 * @since 1.0-SNAPSHOT
 */
public class Cupboard {

    static Bowl bowl4 = new Bowl(4);
    static Bowl bowl5 = new Bowl(5);
    Bowl bowl3 = new Bowl(3);


    public Cupboard() {
        System.out.println("cupboard()");
        bowl4.f1(2);
    }

    public void f3(int marker) {
        System.out.println("f3(" + marker + ")");
    }
}
