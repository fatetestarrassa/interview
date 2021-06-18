package com.icyfate.interview.test.core.proxy.dynamicProxy.cglib;

import net.sf.cglib.proxy.Enhancer;

/**
 * @author sunbing
 * @version 1.0
 * @since 2021/6/17 17:45
 */
public class CglibProxyFactory {
    public static Object getProxy(Class<?> clazz){
        Enhancer enhancer = new Enhancer();
        enhancer.setClassLoader(clazz.getClassLoader());
        enhancer.setSuperclass(clazz);
        enhancer.setCallback(new DebugMethodInterceptor());

        return enhancer.create();
    }
}
