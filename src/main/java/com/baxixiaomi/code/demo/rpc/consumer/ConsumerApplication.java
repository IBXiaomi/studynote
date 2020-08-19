package com.baxixiaomi.code.demo.rpc.consumer;

import com.baxixiaomi.code.demo.rpc.provider.UserService;
import com.baxixiaomi.code.demo.rpc.register.RpcRegister;

/**
 * 消费
 *
 * @author 吧嘻小米
 * @date 2020/06/28
 */
public class ConsumerApplication {
    public static void main(String[] args) {
        UserService userService = RpcRegister.refer(UserService.class, "127.0.0.1", 1234);
        String sayHello = userService.sayHello();
        System.out.println(sayHello);
    }
}
