package com.baxixiaomi.studynotes.test.chapter5.InitStaticVariable;

/**
 * 定义table类
 *
 * @author 吧嘻小米
 * @since 1.0-SNAPSHOT
 */
public class Table {
    static Bowl bowl1 = new Bowl(1);
    static Bowl bowl2 = new Bowl(2);

    public Table() {
        System.out.println("Table()");
        bowl2.f1(1);
    }

    public void f2(int marker) {
        System.out.println("f2(" + marker + ")");
    }
}
