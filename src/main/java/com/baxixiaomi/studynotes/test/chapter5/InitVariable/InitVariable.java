package com.baxixiaomi.studynotes.test.chapter5.InitVariable;

/**
 * 测试变量的加载顺序
 *
 * @author 吧嘻小米
 * @since 1.0-SNAPSHOT
 */
public class InitVariable {
    public static void main(String[] args) {
        /* 在类的内部，变量定义的先后顺序决定了初始化的顺序，变量会在
         任何方法(包括构造方法)被调用之前得到初始化*/
        House h = new House();
        h.f();
    }

}
