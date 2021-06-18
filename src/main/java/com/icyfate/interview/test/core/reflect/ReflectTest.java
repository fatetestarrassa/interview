package com.icyfate.interview.test.core.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 反射：
 *      优点：让代码更加灵活
 *      缺点：通过取消安全检查，会增加安全隐患，性能稍差
 *
 * @author sunbing
 * @version 1.0
 * @since 2021/6/16 17:33
 */
public class ReflectTest {
    public static void main(String[] args) throws Exception {
        Class targetClass = Class.forName("com.icyfate.interview.test.core.reflect.TargetObject");
        TargetObject targetObject = (TargetObject)targetClass.newInstance();

        /**
         * 获取类中所有方法
         */
        Method[] methods = targetClass.getDeclaredMethods();
        for(Method method : methods){
            System.out.println(method.getName());
        }

        /**
         * 获取指定方法并调用
         */
        Method publicMethod = targetClass.getDeclaredMethod("publicMethod",String.class);
        publicMethod.invoke(targetObject,"abc");

        /**
         * 获取变量
         */
        Field field = targetClass.getDeclaredField("value");
        field.setAccessible(true);
        field.set(targetObject,"123");

        /**
         * 获取私有方法，取消安全检查，有风险
         */
        Method privateMethd = targetClass.getDeclaredMethod("privateMethd");
        privateMethd.setAccessible(true);
        privateMethd.invoke(targetObject);
    }
}
