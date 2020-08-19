package com.baxixiaomi.studynotes.test.chapter3;

public class AutomaticIncreaseAndDecrease {
    public static void main(String[] args) {
        int i = 1;
        System.out.println("i = " + i );//1
        System.out.println("i++ = " + i++ );//1
        System.out.println("++i = " + ++i );//3
        System.out.println("i = " + i );//3
        System.out.println("i-- = " + i-- );//3
        System.out.println("--i = " + --i );//1
        System.out.println("i = " + i );//1
    }
}
