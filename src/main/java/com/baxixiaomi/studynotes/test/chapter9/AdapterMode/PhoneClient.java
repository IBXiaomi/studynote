package com.baxixiaomi.studynotes.test.chapter9.AdapterMode;

import com.baxixiaomi.studynotes.test.chapter9.AdapterMode.adapterpakcage.AdapterVoltage;

public class PhoneClient {

    public static void main(String[] args) {
        System.out.println("类适配器模式");
        Phone phone = new Phone();
        phone.charging(new AdapterVoltage());
    }
}
