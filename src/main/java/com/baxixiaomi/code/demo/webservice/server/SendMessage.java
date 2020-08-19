package com.baxixiaomi.code.demo.webservice.server;

import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * webservice服务端接口
 *
 * @author 吧嘻小米
 * @since 1.0-SNAPSHOT
 */
@WebService
public interface SendMessage {

    @WebMethod
    void sendMessage();
}
