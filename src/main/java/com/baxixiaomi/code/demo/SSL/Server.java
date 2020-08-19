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
 * 双向认证服务端
 *
 * @author 吧嘻小米
 * @date 2020/06/03
 */
@Slf4j
public class Server implements HandshakeCompletedListener {

    private static final String SERVER_CER = "Z:\\TEST\\wjwserver.jks";

    private static final String SERVER_PASSWORD = "wang950213";

    private static final String TRUST_STONE = "Z:\\TEST\\wjwtrust.jks";

    private static final String TRUST_STONE_PASSWORD = "wang950213";


    public static void main(String[] args) {
        try {
            KeyManagerFactory keyManagerFactory = keyStoreCertification(SERVER_CER, SERVER_PASSWORD);

            TrustManagerFactory trustManagerFactory = trustStoreCertification(TRUST_STONE, TRUST_STONE_PASSWORD);

            SSLContext sslContext = SSLContext.getInstance("TLSv1.2");
            sslContext.init(keyManagerFactory.getKeyManagers(), trustManagerFactory.getTrustManagers(), null);

            SSLServerSocketFactory serverSocketFactory = sslContext.getServerSocketFactory();
            SSLServerSocket sslServerSocket = (SSLServerSocket) serverSocketFactory.createServerSocket(8443);
            sslServerSocket.setNeedClientAuth(true);
            Socket socket = sslServerSocket.accept();


            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader userBr = new BufferedReader(new InputStreamReader(System.in));
            SSLCertification.serverSendAndReceiveMessage(br, pw, userBr);

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

    @Override
    public void handshakeCompleted(HandshakeCompletedEvent handshakeCompletedEvent) {

    }
}
