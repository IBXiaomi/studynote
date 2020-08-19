package com.baxixiaomi.studynotes.test.chapter7.Inheritanceandinitialization;

/**
 * 展示继承和初始化的过程
 * 首先访问的是Beetle.main方法
 * 当所有的类加载完成(存在static，即被初始化)，开始创建对象，(如果存在继承关系，父类的构造器会被自动调用)
 *
 * @author 吧嘻小米
 * @since 1。0-SNAPSHOT
 */
public class Beetle extends Insect {
    private static int k = printInit("Beetle.k initialized");

    public Beetle() {
        System.out.println("K = " + k);
        System.out.println("j = " + j);
    }

    private static int x2 = printInit("static Beetle.x2 initialized");

    public static void main(String[] args) {
        System.out.println("Beetle constructor");
        Beetle b = new Beetle();
    }
}/*Output:
   static Insect.x1 initialized
   Beetle.k initialized
   static Beetle.x2 initialized
   Beetle constructor
   i = 9, j = 0
   K = 47
   j = 36
   *///:~
