package com.icyfate.interview.test.core.proxy.dynamicProxy.cglib;

/**
 * @author sunbing
 * @version 1.0
 * @since 2021/6/17 17:40
 */
public class AliSmsService {
    public String send(String message) {
        System.out.println("send message:" + message);
        return message;
    }
}
