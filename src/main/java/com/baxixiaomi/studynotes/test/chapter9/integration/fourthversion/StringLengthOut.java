package com.baxixiaomi.studynotes.test.chapter9.integration.fourthversion;

import com.baxixiaomi.studynotes.test.chapter9.integration.secondversion.ISSort;
import com.baxixiaomi.studynotes.test.chapter9.integration.thirdversion.DealClassEnum;
import com.baxixiaomi.studynotes.test.chapter9.integration.thirdversion.DealClassFastory;

import java.util.ArrayList;
import java.util.List;

/**
 * 拆分if else
 *
 * @author 吧嘻小米
 * @date 2020/04/30
 */
public class StringLengthOut {
    private static final int stringLength = 1;

    private static List<SplitMethod> list = new ArrayList<>();

    public StringLengthOut() {
        list.add(new SplitMethod(0, stringLength, DealClassFastory.getDealClass(DealClassEnum.Fast)));
        list.add(new SplitMethod(stringLength, 4 * stringLength, DealClassFastory.getDealClass(DealClassEnum.Faster)));
        list.add(new SplitMethod(4 * stringLength, Integer.MAX_VALUE, DealClassFastory.getDealClass(DealClassEnum.Fastest)));
    }

    private void selectMethod(String string) {
        int strLength = string.length();
        for (SplitMethod splitMethod : list) {
            if (splitMethod.inRange(strLength)) {
                splitMethod.sort(strLength);
            }
        }
    }


    public static void main(String[] args) {
        StringLengthOut stringLengthOut = new StringLengthOut();
        stringLengthOut.selectMethod("sssss");
    }
}
