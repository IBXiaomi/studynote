package com.baxixiaomi.studynotes.test.chapter3;

/**
 * 展示"="符号引起的别名现象
 *
 * @author 吧嘻小米
 * @since 1.0-SNAPSHOT
 */
public class AliasPhenomenon {
    private Integer number;

    public static class Assigment {
        public static void main(String[] args) {
            AliasPhenomenon aliasPhenomenon1 = new AliasPhenomenon();
            AliasPhenomenon aliasPhenomenon2 = new AliasPhenomenon();
            aliasPhenomenon1.number = 13;
            aliasPhenomenon2.number = 14;
            System.out.println("aliasPhenomenon1.number = " + aliasPhenomenon1.number +
                    " , aliasPhenomenon2.number  = " + aliasPhenomenon2.number);
            // "="进行赋值操作,实际上将aliasPhenomenon2对象的引用复制到aliasPhenomenon1
            aliasPhenomenon1 = aliasPhenomenon2;
            System.out.println("aliasPhenomenon1.number = " + aliasPhenomenon1.number +
                    " , aliasPhenomenon2.number  = " + aliasPhenomenon2.number);
            // "="再次进行赋值，证明操作的是对象的引用
            aliasPhenomenon1.number = 15;
            System.out.println("aliasPhenomenon1.number = " + aliasPhenomenon1.number +
                    " , aliasPhenomenon2.number  = " + aliasPhenomenon2.number);

        }
    }
}
