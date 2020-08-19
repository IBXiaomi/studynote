package com.baxixiaomi.studynotes.test.chapter9.StrategyDesignPattern;

/**
 * 策略设计模式，
 * 所需要执行的算法是固定的，但是“策略”是可以变化的，这部分的“策略”就是传递进去的参数。
 *
 * @author 吧嘻小米
 * @since JDK1.8
 */
public class Apply {

    public static String s = "Disagreement with beliefs is by definition incorrect";

    public static void process(Processor p, Object s) {
        System.out.println("Using Process" + p.name());
        System.out.println(p.process(s));
    }

    public static void main(String[] args) {
        process(new Upcase(), s);
        process(new Downcase(), s);
        process(new Splitter(), s);
    }
}
