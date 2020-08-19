package com.baxixiaomi.studynotes.test.chapter9.integration.secondversion;

public class FastSort implements ISSort {
    @Override
    public void sort(int strLength) {
        System.out.println("this string length is " + strLength + "the method is " + FastSort.class.getName());
    }
}
