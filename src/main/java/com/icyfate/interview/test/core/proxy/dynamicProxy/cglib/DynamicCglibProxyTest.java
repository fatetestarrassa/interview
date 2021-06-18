package com.icyfate.interview.test.core.proxy.dynamicProxy.cglib;

/**
 * @author sunbing
 * @version 1.0
 * @since 2021/6/17 17:49
 */
public class DynamicCglibProxyTest {
    public static void main(String[] args) {
        AliSmsService aliSmsService = (AliSmsService)CglibProxyFactory.getProxy(AliSmsService.class);
        aliSmsService.send("中奖了");
    }
}
