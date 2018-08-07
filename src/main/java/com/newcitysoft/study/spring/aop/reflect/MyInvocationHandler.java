package com.newcitysoft.study.spring.aop.reflect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author lixin.tian@renren-inc.com
 * @date 2018/8/7 10:08
 */
public class MyInvocationHandler implements InvocationHandler {

    private Object target;

    public MyInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if(method.getName().equals("request")) {
            String name = target.getClass().getName();
            System.out.println("class name : "+ name);
            String typeName = target.getClass().getSimpleName();
            System.out.println("simple name : " + typeName);
            System.out.println("MyInvocationHandler...");
        }

        return method.invoke(target, args);
    }
}
