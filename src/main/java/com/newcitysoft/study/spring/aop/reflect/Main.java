package com.newcitysoft.study.spring.aop.reflect;

import java.lang.reflect.Proxy;

/**
 * @author lixin.tian@renren-inc.com
 * @date 2018/8/7 10:10
 */
public class Main {

    public static void main(String[] args) {
        ISubject subject = (ISubject) Proxy.newProxyInstance(Main.class.getClassLoader(),
                new Class[]{ISubject.class},
                new MyInvocationHandler(new SubjectImpl()));
        IRequestable requestable = (IRequestable) Proxy.newProxyInstance(Main.class.getClassLoader(),
                new Class[]{IRequestable.class},
                new MyInvocationHandler(new RequestableImpl()));

        subject.request();
        requestable.request();
    }
}
