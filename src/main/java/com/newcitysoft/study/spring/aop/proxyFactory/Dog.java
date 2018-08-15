package com.newcitysoft.study.spring.aop.proxyFactory;

/**
 * @author lixin.tian@renren-inc.com
 * @date 2018/8/15 10:23
 */
public class Dog implements Animal {

    @Override
    public void eat() {
        System.out.println("狗吃饭...");
    }
}
