package com.baxixiaomi.studynotes.test.chapter4;

import java.util.Random;

/**
 * 演示switch关键字
 *
 * @author 吧嘻小米
 * @since 1.0-SNAPSHOT
 */
public class SwitchTest {
    public static void main(String[] args) {
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            int c = random.nextInt(26) + 'a';
            // switch后面只能跟int或者char类型的数据
            switch (c) {
                case 'a':
                    System.out.println("当前的数值是" + c);
                    System.out.println("当前的参数是" + 'a');
                    break;
                case 'b':
                    System.out.println("当前的参数是" + 'b');
                    break;
                case 'c':
                    System.out.println("当前的参数是" + 'c');
                    break;
                case 'z':
                    System.out.println("当前的参数是" + 'z');
                    break;
                default:
                    System.out.println("当前的参数是" + c);
                    // 此处的break可以省略，统一代码风格，可以添加，没有任何意义
                    break;
            }
        }
    }
}
