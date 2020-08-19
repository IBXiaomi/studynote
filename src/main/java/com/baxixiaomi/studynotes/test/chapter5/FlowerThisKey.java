package com.baxixiaomi.studynotes.test.chapter5;

/**
 * this关键字的演示
 *
 * @author 吧嘻小米
 * @since 1.0-SNAPSHOT
 */
public class FlowerThisKey {
    private int petalCount = 0;
    private String s = "initial value";

    // 构造方法1
    private FlowerThisKey(int petal) {
        petalCount = petal;
        System.out.println("Constructor w/ int arg only ,petalCount = " + petalCount);
    }
    // 构造方法2
    public FlowerThisKey(String ss) {
        System.out.println("Constructor w/ String arg only , s = " + ss);
        s = ss;
    }
    // 构造方法3
    private FlowerThisKey(String s, int petals) {
        this(petals);
        this.s = s;
        System.out.println("String & int args");

    }
    // 构造方法4
    private FlowerThisKey() {
        this("hi", 47);
        System.out.println("default constructor (no args)");
    }
    // 无返回值的方法
    private void printPetalCount() {
        System.out.println("petalCount = " + petalCount + ", s = " + s);
    }

    public static void main(String[] args) {
        FlowerThisKey x = new FlowerThisKey();
        x.printPetalCount();
    }
}
