package com.baxixiaomi.code.demo.bio;

import java.net.Socket;
import java.util.Date;

/**
 * BIO模型的客户端
 *
 * @author 吧嘻小米
 * @date 2020/06/16
 */
public class BIOClient {
    public static void main(String[] args) {
        new Thread(() -> {
            try {
                Socket socket = new Socket("127.0.0.1", 2130);
                while (true) {
                    socket.getOutputStream().write((new Date() + "hello baxixiaomi").getBytes());
                    Thread.sleep(2000);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
}
