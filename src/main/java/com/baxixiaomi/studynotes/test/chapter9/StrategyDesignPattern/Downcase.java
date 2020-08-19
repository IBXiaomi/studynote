package com.baxixiaomi.studynotes.test.chapter9.StrategyDesignPattern;

public class Downcase extends Processor {
    String process(Object input) {
        return ((String) input).toLowerCase();
    }
}
