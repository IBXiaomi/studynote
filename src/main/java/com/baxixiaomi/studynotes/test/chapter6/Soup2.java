package com.baxixiaomi.studynotes.test.chapter6;

/**
 * 包访问权限
 *
 * @author 吧嘻小米
 * @since 1.0-SNAPSHOT
 */
class Soup2 {
    /**
     * 私有的构造方法,不能通过new的方式获取该类的对象
     */
    private Soup2() {
    }

    /**
     * 单例的设计模式,程序运行期间只存在一个Soup2的对象，因为static的存在
     */
    private static Soup2 soup2 = new Soup2();

    public static Soup2 makeSoup2() {
        return soup2;
    }

    void f() {
        System.out.println(soup2);
    }
}
