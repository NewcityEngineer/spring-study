package com.newcitysoft.study.spring.aop.cglib;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author lixin.tian@renren-inc.com
 * @date 2018/8/7 10:23
 */
public class RequestCtrlCallback implements MethodInterceptor {

    @Override
    public Object intercept(Object object, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        if(method.getName().equals("request")) {
            System.out.println("RequestCtrlCallback...");
            return methodProxy.invokeSuper(object, args);
        }

        return null;
    }
}
