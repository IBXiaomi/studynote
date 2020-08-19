package com.baxixiaomi.studynotes.test.chapter7.Inheritanceandinitialization;

 class Insect {
    private int i =9;
    protected int j;
    public Insect(){
        System.out.println("i = " + i + ", j = " + j);
        j = 36;
    }
    private static int x1 = printInit("static Insect.x1 initialized");

     static int printInit(String s){
        System.out.println(s);
        return 47;
    }
}
