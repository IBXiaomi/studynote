package com.baxixiaomi.studynotes.test.chapter9.integration.secondversion;

/**
 * 采用接口实现
 *
 * @author 吧嘻小米
 * @date 2020/04/30
 */
public class StringLengthOut {
    private static final int stringLength = 1;

    private void selectMethod(String string) {
        int strLength = string.length();
        ISSort isSort = null;
        if (strLength <= stringLength) {
            isSort = new FastSort();
            isSort.sort(strLength);
        } else if (strLength <= 4 * stringLength) {
            isSort = new FasterSort();
            isSort.sort(strLength);
        } else {
            isSort = new FastestSort();
            isSort.sort(strLength);
        }
    }


    public static void main(String[] args) {
        StringLengthOut stringLengthOut = new StringLengthOut();
        stringLengthOut.selectMethod("sssss");
    }
}
