package com.baxixiaomi.code.demo.bio;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * BIO模型的服务端
 *
 * @author 吧嘻小米
 * @date 2020/06/16
 */
public class BIOServer {
    public static void main(String[] args) {
        new Thread(() -> {
            try {
                ServerSocket serverSocket = new ServerSocket(2130);
                while (true) {
                    Socket socket = serverSocket.accept();
                    int len;
                    byte[] data = new byte[1024];
                    InputStream inputStream = socket.getInputStream();
                    while ((len = inputStream.read(data)) != -1) {
                        System.out.println(new String(data, 0, len));
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
