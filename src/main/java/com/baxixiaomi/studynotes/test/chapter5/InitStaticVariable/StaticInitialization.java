package com.baxixiaomi.studynotes.test.chapter5.InitStaticVariable;

/**
 * 变量初始化的顺序演示
 * 静态变量优先于其他变量，先进行初始化，其次是普通的变量的初始化;并且只有当一个变量
 * 完全的执行完初始化时，才会进行第二个变量的初始化
 *
 * @author 吧嘻小米
 * @since 1.0-SNAPSHOT
 */
public class StaticInitialization {

    static Table table = new Table();
    static Cupboard cupboard = new Cupboard();

    public static void main(String[] args) {
        System.out.println("create new Cupboard() in main");
        new Cupboard();
        System.out.println("Create new Cupboard() in main");
        new Cupboard();
        table.f2(1);
        cupboard.f3(1);
    }/*Output:
     Bowl(1)
     Bowl(2)
     Table()
     f1(1)
     Bowl(4)
     Bowl(5)
     Bowl(3)
     cupboard()
     f1(2)
     create new Cupboard() in main
     Bowl(3)
     cupboard()
     f1(2)
     Create new Cupboard() in main
     Bowl(3)
     cupboard()
     f1(2)
     f2(1)
     f3(1)
    *///:~
}
