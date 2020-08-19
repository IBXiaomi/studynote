package com.baxixiaomi.studynotes.test.chapter9.FactoryMode;

public class ImplService2Factory implements ServiceFactory {
    @Override
    public Service getService() {
        return new ImplService2();
    }
}
