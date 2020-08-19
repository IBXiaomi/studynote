package com.baxixiaomi.studynotes.test.chapter9.AdapterMode.srcpackage;

/**
 * 被适配的类
 */
public class Voltage220V {

    private int src = 220;

    public int output220V() {
        System.out.println("电压220v~~");
        return src;
    }
}
