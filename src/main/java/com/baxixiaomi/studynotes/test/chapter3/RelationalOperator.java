package com.baxixiaomi.studynotes.test.chapter3;

public class RelationalOperator {
    private int i;
    public static void main(String[] args) {
        Integer integer1 = new Integer(47);
        Integer integer2 = new Integer(47);
        System.out.println(integer1 == integer2);
        System.out.println(integer1 != integer2);
        RelationalOperator r1 = new RelationalOperator();
        RelationalOperator r2 = new RelationalOperator();
        r1.i = r2.i = 100;
        System.out.println(r1.equals(r2));
        float f = 1e-43f;
        System.out.println(f);
    }
}
