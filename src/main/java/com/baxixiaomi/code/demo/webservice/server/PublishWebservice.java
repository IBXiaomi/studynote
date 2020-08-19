package com.baxixiaomi.code.demo.webservice.server;

import com.baxixiaomi.code.demo.webservice.server.impl.SendMessageImpl;
import org.apache.cxf.jaxws.JaxWsServerFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 本地发布webservice服务
 *
 * @author 吧嘻小米
 * @since 1.0-SNAPSHOT
 */
public class PublishWebservice {
    private static Logger LOGGER = LoggerFactory.getLogger(PublishWebservice.class);

    public static void main(String[] args) {
        LOGGER.info("start publish webservice");
        JaxWsServerFactoryBean factoryBean = new JaxWsServerFactoryBean();
        factoryBean.setAddress("http://localhost:12121");
        factoryBean.setServiceClass(SendMessageImpl.class);
        factoryBean.create();
    }

}
