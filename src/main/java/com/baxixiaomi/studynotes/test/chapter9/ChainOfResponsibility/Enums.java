package com.baxixiaomi.studynotes.test.chapter9.ChainOfResponsibility;

import java.util.Random;

public class Enums {
    private static int random;
    // random数值的作用时当出现两个随机rand时，保证两次随机数相同
    private static Random rand = new Random(random);

    protected static <T extends Enum<T>> T random(Class<T> ec) {
        // 设置随机数的大小，避免数组越界异常
        // Math.random()方法返回的是[0.0--1.0)之间的Double值,使用int强转只会保留小数点之前的数字.
        // new Random().nextInt(n)返回的是[0--n)之间的int值
        random = (int) (Math.random() * ec.getEnumConstants().length);
        return random(ec.getEnumConstants());
    }

    private static <T> T random(T[] values) {
        // 当随机数为0时，nextInt()方法会抛出方法参数异常 ：IllegalArgumentException:--->bound must be positive
        // 因此对当随机数为0时限制
        if (values.length == 0) {
            return values[0];
        }
        return values[rand.nextInt(values.length)];
    }
}
