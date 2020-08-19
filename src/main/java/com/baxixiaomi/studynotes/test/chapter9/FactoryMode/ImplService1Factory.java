package com.baxixiaomi.studynotes.test.chapter9.FactoryMode;

public class ImplService1Factory implements ServiceFactory {
    @Override
    public Service getService() {
        return new ImplService1();
    }
}
