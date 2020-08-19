package com.baxixiaomi.studynotes.test.chapter4;

/**
 * 验证break和continue关键字，并且是在标签的环境下
 *
 * @author 吧嘻小米
 * @since 1.0-SNAPSHOT
 */

public class BreakAndContinue {
    public static void main(String[] args) {
        int i = 0;
        // 外部标签，定位
        outer:
        while (true) {
            // 内部标签
            inner:
            for (; i < 10; i++) {
                System.out.println("i=" + i);
                if (i == 2) {
                    System.out.println("continue");
                    // 停止本次内部循环，继续执行下一次的循环
                    continue;
                }
                if (i == 3) {
                    System.out.println("break");
                    // 停止本次内部循环
                    i++;
                    break;
                }
                if (i == 7) {
                    System.out.println("continue outer");
                    // 停止本次内部循环，下一次循环从外部开始
                    i++;
                    continue outer;
                }
                if (i == 8) {
                    System.out.println("break outer");
                    // 直接停止内外部循环
                    break outer;
                }
                for (int k = 0; k < 5; k++) {
                    System.out.println("continue inner");
                    // 停止本次循环，下一次循环从内部循环开始
                    continue inner;
                }
            }
        }
    }
}
