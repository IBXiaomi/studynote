package com.baxixiaomi.studynotes.test.chapter9.integration.secondversion;

public class FastestSort implements ISSort{

    @Override
    public void sort(int strLength) {
        System.out.println("this string length is " + strLength + "the method is " + FastestSort.class.getName());
    }
}
