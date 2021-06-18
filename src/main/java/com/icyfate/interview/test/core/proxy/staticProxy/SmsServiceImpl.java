package com.icyfate.interview.test.core.proxy.staticProxy;

/**
 * @author sunbing
 * @version 1.0
 * @since 2021/6/17 16:05
 */
public class SmsServiceImpl implements SmsService {

    @Override
    public String send(String message) {
        System.out.println("send message:" + message);
        return message;
    }
}
