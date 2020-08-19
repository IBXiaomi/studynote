package com.baxixiaomi.studynotes.test.chapter9.AdapterMode;

import com.baxixiaomi.studynotes.test.chapter9.AdapterMode.dstpackage.IVoltage5V;

public class Phone {

    public void charging(IVoltage5V iVoltage5V) {
        if (iVoltage5V.output5v() == 5) {
            System.out.println("开始充电");
        } else if (iVoltage5V.output5v() > 5) {
            System.out.println("电压过高");
        }
    }
}
