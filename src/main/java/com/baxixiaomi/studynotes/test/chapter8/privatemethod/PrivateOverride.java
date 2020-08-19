package com.baxixiaomi.studynotes.test.chapter8.privatemethod;

/**
 * 展示private的方法并不会被覆盖
 * 推荐在导出类中对于基类中的private方法(private的方法默认是final的)，采用不同的名称
 *
 * @author 吧嘻小米
 * @since 1.0-SNAPSHOT
 */
public class PrivateOverride {
    private void f() {
        System.out.println("private f()");
    }

    public static void main(String[] args) {
        PrivateOverride privateOverride = new Derived();
        // 期望是public f() 实际是private f()
        privateOverride.f();
    }
}
