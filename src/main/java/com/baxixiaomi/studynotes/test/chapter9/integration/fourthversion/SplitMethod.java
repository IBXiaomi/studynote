package com.baxixiaomi.studynotes.test.chapter9.integration.fourthversion;

import com.baxixiaomi.studynotes.test.chapter9.integration.secondversion.ISSort;

/**
 * 主要作为拆分if else的功能
 *
 * @author 吧嘻小米
 * @date 2020/04/30
 */
public class SplitMethod {

    private int start;

    private int end;

    private ISSort isSort;

    public SplitMethod(int start, int end, ISSort isSort) {
        this.start = start;
        this.end = end;
        this.isSort = isSort;
    }

    public boolean inRange(int stringLength) {
        return start < stringLength && stringLength <= end;
    }

    public void sort(int strLength) {
        isSort.sort(strLength);
    }
}
