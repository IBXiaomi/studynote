package com.baxixiaomi.code.demo.rpc.provider.impl;

import com.baxixiaomi.code.demo.rpc.provider.UserService;

/**
 * 服务提供者
 *
 * @author 吧嘻小米
 * @date 2020/06/28
 */
public class UserServiceImpl implements UserService {

    @Override
    public String sayHello() {
        return "Hello baxixiaomi";
    }
}
