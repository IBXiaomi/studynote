package com.baxixiaomi.studynotes.test.chapter9.StrategyDesignPattern;

public class Processor {
    public String name() {
        return getClass().getSimpleName();
    }

    Object process(Object input) {
        return input;
    }
}
