package com.icyfate.interview.test.core.proxy.dynamicProxy.jdk;

import java.lang.reflect.Proxy;

/**
 * @author sunbing
 * @version 1.0
 * @since 2021/6/17 17:26
 */
public class JdkProxyFactory {

    public static Object getProxy(Object target){
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                new DebugInvocationHandler(target));
    }
}
