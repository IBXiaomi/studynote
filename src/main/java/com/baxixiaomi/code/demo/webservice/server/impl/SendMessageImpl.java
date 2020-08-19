package com.baxixiaomi.code.demo.webservice.server.impl;

import com.baxixiaomi.code.demo.webservice.server.SendMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jws.WebService;


/**
 * 服务的实现类
 *
 * @author 吧嘻小米
 * @since 1.0-SNAPSHOT
 */
@WebService(targetNamespace = "http://impl.server.webservice.baxixiaomi.com", name = "SendMessageImpl")
public class SendMessageImpl implements SendMessage {

    private static Logger LOGGER = LoggerFactory.getLogger(SendMessageImpl.class);

    @Override
    public void sendMessage() {
        System.out.println(1);
    }
}
