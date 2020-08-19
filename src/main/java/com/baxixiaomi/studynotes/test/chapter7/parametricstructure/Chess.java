package com.baxixiaomi.studynotes.test.chapter7.parametricstructure;

/**
 * 展示基类和继承类之间存在有参构造时，继承类必须使用super关键字调用基类的有参构造
 *
 * @author 吧嘻小米
 * @since 1.0-SNAPSHOT
 */
 class Chess extends BoardGame {
    public Chess(int i) {
        super(2);
        System.out.println("Chess constructor :" + i);
    }

    public static void main(String[] args) {
        Chess chess = new Chess(3);
    }
}
