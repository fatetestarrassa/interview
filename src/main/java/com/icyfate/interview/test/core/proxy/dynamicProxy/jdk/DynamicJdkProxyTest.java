package com.icyfate.interview.test.core.proxy.dynamicProxy.jdk;

import com.icyfate.interview.test.core.proxy.staticProxy.SmsService;
import com.icyfate.interview.test.core.proxy.staticProxy.SmsServiceImpl;

/**
 * @author sunbing
 * @version 1.0
 * @since 2021/6/17 17:28
 */
public class DynamicJdkProxyTest {
    public static void main(String[] args) {
        SmsService smsService = (SmsService) JdkProxyFactory.getProxy(new SmsServiceImpl());
        smsService.send("中奖了");
    }
}
