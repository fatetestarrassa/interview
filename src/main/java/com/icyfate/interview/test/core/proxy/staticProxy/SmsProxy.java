package com.icyfate.interview.test.core.proxy.staticProxy;

/**
 * @author sunbing
 * @version 1.0
 * @since 2021/6/17 16:06
 */
public class SmsProxy implements SmsService {

    private SmsService smsService;

    public SmsProxy(SmsService smsService) {
        this.smsService = smsService;
    }

    @Override
    public String send(String message) {
        //调用方法之前，我们可以添加自己的操作
        System.out.println("before method send()");
        smsService.send(message);
        //调用方法之后，我们同样可以添加自己的操作
        System.out.println("after method send()");
        return null;
    }

}
