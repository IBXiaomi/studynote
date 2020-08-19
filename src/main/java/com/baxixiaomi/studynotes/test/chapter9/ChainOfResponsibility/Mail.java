package com.baxixiaomi.studynotes.test.chapter9.ChainOfResponsibility;

import java.util.Iterator;

/**
 * 责任链模式，创建不同的解决方式将他们连接在一起解决一个问题，
 * 当请求到来时，遍历这个链，直到链中的某种解决方式能够处理该请求
 *
 * @author 吧嘻小米
 * @date 2020/04/22
 */
public class Mail {
    enum GeneralDelivery {
        YES, NO1, NO2, NO3, NO4, NO5
    }

    enum Scannability {
        UNSCANNABLE, YES1, YES2, YES3, YES4
    }

    enum Readability {
        ILIEGIBLE, YES1, YES2, YES3, YES4
    }

    enum Address {
        INCORRECT, OK1, OK2, OK3, OK4, OK5, OK6
    }

    enum ReturnAddress {
        MISSING, OK1, OK2, OK3, OK4, OK5
    }

    GeneralDelivery generalDelivery;
    Scannability scanability;
    Readability readability;
    Address address;
    ReturnAddress returnAddress;
    static long counter = 0;
    long id = counter++;

    public String toString() {
        return "Mail" + id;
    }

    public String details() {
        return toString() +
                ", General Delivery:" + generalDelivery +
                ", Address Scannability:" + scanability +
                ", Address Readability:" + readability +
                ", Address Address:" + address +
                ", Return address:" + returnAddress;
    }

    public static Mail randomMail() {
        Mail mail = new Mail();
        mail.generalDelivery = Enums.random(GeneralDelivery.class);
        mail.scanability = Enums.random(Scannability.class);
        mail.readability = Enums.random(Readability.class);
        mail.address = Enums.random(Address.class);
        mail.returnAddress = Enums.random(ReturnAddress.class);
        return mail;
    }

    public static Iterable<Mail> generator(final int count) {
        return new Iterable<Mail>() {
            int n = count;

            @Override
            public Iterator<Mail> iterator() {
                return new Iterator<Mail>() {
                    @Override
                    public boolean hasNext() {
                        return n-- > 0;
                    }

                    @Override
                    public Mail next() {
                        return randomMail();
                    }

                    public void remove() {
                        throw new UnsupportedOperationException();
                    }
                };
            }
        };
    }
}
