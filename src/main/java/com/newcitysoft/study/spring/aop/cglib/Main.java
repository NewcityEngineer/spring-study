package com.newcitysoft.study.spring.aop.cglib;

import org.springframework.cglib.proxy.Enhancer;

/**
 * @author lixin.tian@renren-inc.com
 * @date 2018/8/7 10:26
 */
public class Main {

    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();

        enhancer.setSuperclass(Requestable.class);
        enhancer.setCallback(new RequestCtrlCallback());

        Requestable requestable = (Requestable) enhancer.create();
        requestable.request();
    }
}
