package com.icyfate.interview.test.core.proxy.dynamicProxy.cglib;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author sunbing
 * @version 1.0
 * @since 2021/6/17 17:41
 */
public class DebugMethodInterceptor implements MethodInterceptor {


    /**
     *
     * @param obj       被代理的对象（需要增强的对象）
     * @param method    被拦截的方法（需要增强的方法）
     * @param args      方法参数
     * @param methodProxy     用于调用原方法
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {

        //调用方法之前，我们可以添加自己的操作
        System.out.println("before method " + method.getName());
        Object object = methodProxy.invokeSuper(obj, args);
        //调用方法之后，我们同样可以添加自己的操作
        System.out.println("after method " + method.getName());
        return object;
    }
}
