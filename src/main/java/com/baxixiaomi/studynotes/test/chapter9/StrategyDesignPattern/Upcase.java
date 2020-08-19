package com.baxixiaomi.studynotes.test.chapter9.StrategyDesignPattern;

public class Upcase extends Processor{
    String process(Object input){
        return ((String)input).toUpperCase();
    }
}
