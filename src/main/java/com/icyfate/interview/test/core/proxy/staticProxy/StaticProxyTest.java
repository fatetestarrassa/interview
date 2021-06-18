package com.icyfate.interview.test.core.proxy.staticProxy;

/**
 * @author sunbing
 * @version 1.0
 * @since 2021/6/17 16:15
 */
public class StaticProxyTest {
    public static void main(String[] args) {
        SmsService smsService = new SmsServiceImpl();
        SmsProxy proxy = new SmsProxy(smsService);
        proxy.send("中奖了");
    }
}
