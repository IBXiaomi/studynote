package com.baxixiaomi.studynotes.test.chapter9.integration.firstversion;

/**
 * 整合几种设计模式完成字符串长度输出
 *
 * @author 吧嘻小米
 * @date 2020/04/30
 */
public class StringLengthOut {
    private static final int stringLength = 1;

    private void selectMethod(String string) {
        int strLength = string.length();
        if (strLength <= stringLength) {
            fastSort(strLength);
        } else if (strLength <= 4 * stringLength) {
            fasterSort(strLength);
        } else {
            fastestSort(strLength);
        }
    }

    private void fastSort(int strLength) {
        System.out.println("this string length is ," + strLength + ", the method is fastSort");
    }

    private void fasterSort(int strLength) {
        System.out.println("this string length is , " + strLength + ", the method is fasterSort");
    }

    private void fastestSort(int strLength) {
        System.out.println("this string length is ,  " + strLength + " , the method is fastestSort");
    }

    public static void main(String[] args) {
        StringLengthOut stringLengthOut = new StringLengthOut();
        stringLengthOut.selectMethod("sssss");
    }
}
