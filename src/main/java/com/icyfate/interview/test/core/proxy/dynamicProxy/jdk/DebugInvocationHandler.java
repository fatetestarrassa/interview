package com.icyfate.interview.test.core.proxy.dynamicProxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author sunbing
 * @version 1.0
 * @since 2021/6/17 17:17
 */
public class DebugInvocationHandler implements InvocationHandler {

    /**
     * 被代理的实际对象
     */
    private final Object target;

    public DebugInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //方法执行前，我们可以添加自己的操作
        System.out.println("before method " + method.getName());
        Object result = method.invoke(target,args);
        //方法执行后，我们可以添加自己的操作
        System.out.println("after method " + method.getName());
        return result;
    }
}
