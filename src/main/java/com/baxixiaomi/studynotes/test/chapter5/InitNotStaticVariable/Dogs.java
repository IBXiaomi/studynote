package com.baxixiaomi.studynotes.test.chapter5.InitNotStaticVariable;

/**
 * 演示非静态代码块初始化的执行顺序
 *
 * @author 吧嘻小米
 * @since 1.0-SNAPSHOT
 */
public class Dogs {
    Dog dog1;
    Dog dog2;

    {
        dog1 = new Dog(1);
        dog2 = new Dog(2);
        System.out.println("init dogs completed");
    }

    public Dogs() {
        System.out.println("dogs");
    }

    public Dogs(int i) {
        System.out.println("dogs(" + i + ")");
    }

    public static void main(String[] args) {
        System.out.println("inside main()");
        new Dogs();
        System.out.println("new Dogs() completed");
        new Dogs(3);
        System.out.println("new Dogs(3) completed");
    }/*Output:
     inside main()
     dog(1)
     dog(2)
     init dogs completed
     dogs
     new Dogs() completed
     dog(1)
     dog(2)
     init dogs completed
     dog(3)
     new Dogs(3) completed
    *///:~
}
