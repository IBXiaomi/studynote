package com.baxixiaomi.studynotes.test.chapter9.ChainOfResponsibility;

public class PostOffice {
    // 这个枚举类相当于一个责任链，很多解决方案凑到一块
    enum MailHandler {
        GENERAL_DELIVERY {
            boolean handle(Mail mail) {
                switch (mail.generalDelivery) {
                    case YES:
                        System.out.println("Using general delivery for " + mail);
                        return true;
                    default:
                        return false;
                }
            }
        },
        MACHINE_SCAN {
            boolean handle(Mail mail) {
                switch (mail.scanability) {
                    case UNSCANNABLE:
                        return false;
                    default:
                        switch (mail.address) {
                            case INCORRECT:
                                return false;
                            default:
                                System.out.println("Delivering " + mail + "automatically");
                                return true;
                        }
                }
            }
        },
        VISUAL_INSPETION {
            boolean handle(Mail mail) {
                switch (mail.readability) {
                    case ILIEGIBLE:
                        return false;
                    default:
                        switch (mail.address) {
                            case INCORRECT:
                                return false;
                            default:
                                System.out.println("Delivering" + mail + "normally");
                                return true;
                        }
                }
            }
        },
        RETURN_TO_SENDER {
            boolean handle(Mail mail) {
                switch (mail.returnAddress) {
                    case MISSING:
                        return false;
                    default:
                        System.out.println("Returning" + mail + "to sender");
                        return true;
                }
            }
        };

        abstract boolean handle(Mail mail);
    }

    static void handle(Mail mail) {
        for (MailHandler handler : MailHandler.values()) {
            if (handler.handle(mail)) {
                return;
            }
            System.out.println(mail + "is a dead letter");
        }

    }

    public static void main(String[] args) {
        for (Mail mail : Mail.generator(5)) {
            System.out.println(mail.details());
            handle(mail);
            System.out.println("*******");
        }
    }
}
