package com.baxixiaomi.studynotes.test.chapter9.FactoryMode;

public class ImplService1 implements Service {
    ImplService1() {
    }

    @Override
    public void method1() {
        System.out.println("implService1 method1");
    }

    @Override
    public void method2() {
        System.out.println("implService1 method2");
    }
}
