package com.baxixiaomi.studynotes.test.chapter9.integration.thirdversion;

import com.baxixiaomi.studynotes.test.chapter9.integration.secondversion.FastSort;
import com.baxixiaomi.studynotes.test.chapter9.integration.secondversion.FasterSort;
import com.baxixiaomi.studynotes.test.chapter9.integration.secondversion.FastestSort;
import com.baxixiaomi.studynotes.test.chapter9.integration.secondversion.ISSort;

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
            isSort = DealClassFastory.getDealClass(DealClassEnum.Fast);
            isSort.sort(strLength);
        } else if (strLength <= 4 * stringLength) {
            isSort = DealClassFastory.getDealClass(DealClassEnum.Faster);
            isSort.sort(strLength);
        } else {
            isSort = DealClassFastory.getDealClass(DealClassEnum.Fastest);
            isSort.sort(strLength);
        }
    }


    public static void main(String[] args) {
        StringLengthOut stringLengthOut = new StringLengthOut();
        stringLengthOut.selectMethod("sssss");
    }
}
