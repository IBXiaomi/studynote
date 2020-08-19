package com.baxixiaomi.studynotes.test.chapter9.FactoryMode;

/**
 * 工厂模式
 */
public class Factories {
    public static void serviceConsumer(ServiceFactory factory) {
        Service service = factory.getService();
        service.method1();
        service.method2();
    }

    public static void main(String[] args) {
        serviceConsumer(new ImplService1Factory());

        serviceConsumer(new ImplService2Factory());
    }
}
