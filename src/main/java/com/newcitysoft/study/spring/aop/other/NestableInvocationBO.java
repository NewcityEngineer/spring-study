package com.newcitysoft.study.spring.aop.other;

import org.springframework.aop.framework.AopContext;

/**
 * @author lixin.tian@renren-inc.com
 * @date 2018/8/30 10:03
 */
public class NestableInvocationBO {

    public void method1() {
        // 目标方法调用同类的其他成员方法，使用Spring Aop创建的动态代理对象，在调用方法时，
        // 其实是代理对象调用的目标对象，目标对象进行内部调用，所以不会经过拦截。
//        method2();
        // 通过代理上下文获取当前代理对象，从而使用代理对象调用方法会经过拦截器，所以会被拦截到。
        ((NestableInvocationBO) AopContext.currentProxy()).method2();
        System.out.println("method1 executed!");
    }

    public void method2() {
        System.out.println("method2 executed!");
    }
}
