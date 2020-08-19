package com.baxixiaomi.code.demo.SSL;

import lombok.extern.slf4j.Slf4j;

import javax.net.ssl.*;
import java.io.*;
import java.net.Socket;
import java.security.*;
import java.security.cert.CertificateException;

import static com.baxixiaomi.code.demo.SSL.SSLCertification.keyStoreCertification;
import static com.baxixiaomi.code.demo.SSL.SSLCertification.trustStoreCertification;

/**
 * 双向认证客户端
 *
 * @author 吧嘻小米
 * @date 2020/06/03
 */
@Slf4j
public class Client {
    private static final String CLIENT_CER = "Z:\\TEST\\wjwclient.jks";

    private static final String CLIENT_PASSWORD = "wang950213";

    private static final String TRUST_STONE = "Z:\\TEST\\wjwtrust.jks";

    private static final String TRUST_STONE_PASSWORD = "wang950213";


    public static void main(String[] args) {
        try {
            KeyManagerFactory keyManagerFactory = keyStoreCertification(CLIENT_CER, CLIENT_PASSWORD);

            TrustManagerFactory trustManagerFactory = trustStoreCertification(TRUST_STONE, TRUST_STONE_PASSWORD);

            SSLContext sslContext = SSLContext.getInstance("TLSv1.2");
            sslContext.init(keyManagerFactory.getKeyManagers(), trustManagerFactory.getTrustManagers(), null);

            SSLSocketFactory socketFactory = sslContext.getSocketFactory();
            Socket socket = socketFactory.createSocket("localhost", 8443);

            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedReader userBr = new BufferedReader(new InputStreamReader(System.in));
            PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
            SSLCertification.clientSendAndReceiveMessage(br, pw, userBr);

        } catch (KeyStoreException e) {
            System.out.println(e.getMessage() + " KeyStoreException");
        } catch (IOException e) {
            System.out.println(e.getMessage() + " IOException1");
        } catch (CertificateException e) {
            System.out.println(e.getMessage() + " CertificateException");
        } catch (NoSuchAlgorithmException e) {
            System.out.println(e.getMessage() + " NoSuchAlgorithmException");
        } catch (UnrecoverableKeyException e) {
            System.out.println(e.getMessage() + " UnrecoverableKeyException");
        } catch (KeyManagementException e) {
            System.out.println(e.getMessage() + " KeyManagementException");
        }
    }

}
