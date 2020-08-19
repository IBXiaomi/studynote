package com.baxixiaomi.code.demo.rpc.provider;

import com.baxixiaomi.code.demo.rpc.provider.impl.UserServiceImpl;
import com.baxixiaomi.code.demo.rpc.register.RpcRegister;

/**
 * 服务提供者
 *
 * @author 吧嘻小米
 * @date 2020/06/28
 */
public class ProviderApplication {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        RpcRegister.export(userService, 1234);
    }
}
