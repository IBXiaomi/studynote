package com.baxixiaomi.studynotes.test.chapter7.showinherit;

/**
 * 展示继承类在初始化过程中会将所有的基类同样初始化一次
 *
 * @author 吧嘻小米
 * @since 1.0-SNAPSHOT
 */
public class Cartoon extends Drawing {

    /**
     * 当不提供构造方法时，java编译机制会默认创建一个构造方法，同样完成基类的初始化
     */
    public Cartoon() {
        System.out.println("Cartoon constructor");
    }

    public static void main(String[] args) {
        Cartoon cartoon = new Cartoon();
    }
}
