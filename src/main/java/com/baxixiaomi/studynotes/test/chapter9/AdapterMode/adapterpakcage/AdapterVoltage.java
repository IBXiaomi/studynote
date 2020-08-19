package com.baxixiaomi.studynotes.test.chapter9.AdapterMode.adapterpakcage;

import com.baxixiaomi.studynotes.test.chapter9.AdapterMode.dstpackage.IVoltage5V;
import com.baxixiaomi.studynotes.test.chapter9.AdapterMode.srcpackage.Voltage220V;

/**
 * 适配器
 */
public class AdapterVoltage extends Voltage220V implements IVoltage5V {

    @Override
    public int output5v() {
        int src = output220V();
        int dst = src / 44;
        System.out.println("输出电压" + dst);
        return dst;
    }
}
