package com.baxixiaomi.code.demo.SSL;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.TrustManagerFactory;
import java.io.*;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

/**
 * 认证过程
 *
 * @author 吧嘻小米
 * @date 2020/06/03
 */
 class SSLCertification  {


    /**
     *
     * @param cerPath 密钥路径
     * @param password 密钥密码
     * @return KeyManagerFactory
     * @throws KeyStoreException KeyStoreException
     * @throws IOException IOException
     * @throws NoSuchAlgorithmException NoSuchAlgorithmException
     * @throws CertificateException CertificateException
     * @throws UnrecoverableKeyException UnrecoverableKeyException
     */
    static KeyManagerFactory keyStoreCertification(String cerPath, String password) throws KeyStoreException, IOException, NoSuchAlgorithmException, CertificateException, UnrecoverableKeyException {
        KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
        keyStore.load(new FileInputStream(cerPath), password.toCharArray());
        KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
        keyManagerFactory.init(keyStore, password.toCharArray());
        return keyManagerFactory;
    }

    /**
     *
     * @param cerPath 信任库路径
     * @param password 信任库密码
     * @return
     * @throws KeyStoreException KeyStoreException
     * @throws IOException IOException
     * @throws NoSuchAlgorithmException NoSuchAlgorithmException
     * @throws CertificateException CertificateException
     */
    static TrustManagerFactory trustStoreCertification(String cerPath, String password) throws KeyStoreException, IOException, NoSuchAlgorithmException, CertificateException {
        KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
        trustStore.load(new FileInputStream(cerPath), password.toCharArray());
        TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
        trustManagerFactory.init(trustStore);
        return trustManagerFactory;
    }

    /**
     * 客户端接收和发送消息
     * @param br 接收流
     * @param pw 输出流
     * @param userBr 用户输出流
     */
    static void clientSendAndReceiveMessage(BufferedReader br, PrintWriter pw, BufferedReader userBr) {
        try {
            while (true) {
                System.out.print("客户端发出去的消息: ");
                String sendMessage = userBr.readLine().trim();
                pw.println(sendMessage);
                if (sendMessage.trim().equalsIgnoreCase("BYE")) {
                    break;
                } else {
                    System.out.println("Please wait Server Message..");
                }

                String receiveMessage = br.readLine();
                if (receiveMessage != null && !receiveMessage.equals("")) {
                    System.out.println("服务器发过来的消息: " + receiveMessage);
                    if (receiveMessage.trim().equalsIgnoreCase("BYE")) {
                        break;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 服务器端接收和发送消息
     * @param br 接收流
     * @param pw 发送流
     * @param userBr 用户输入流
     */
    static void serverSendAndReceiveMessage(BufferedReader br, PrintWriter pw, BufferedReader userBr) {
        try {
            while (true) {
                System.out.println("等待客户端的请求数据..");
                String receiveMessage = br.readLine().trim();
                if (receiveMessage != null && !receiveMessage.equals("")) {
                    System.out.println("客户端发来的消息: " + receiveMessage);
                    if (receiveMessage.trim().equalsIgnoreCase("BYE")) {
                        break;
                    }
                }

                String sendMessage = userBr.readLine().trim();
                System.out.print("服务器发出去的消息 " + sendMessage);
                pw.println(sendMessage);
                if (sendMessage.trim().equalsIgnoreCase("BYE")) {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
