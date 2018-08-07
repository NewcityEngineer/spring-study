package com.newcitysoft.study.spring.aop.proxy;

/**
 * @author lixin.tian@renren-inc.com
 * @date 2018/8/7 10:03
 */
public class Main {

    public static void main(String[] args) {
        ISubject target = new SubjectImpl();

        SubjectProxy proxy = new SubjectProxy(target);
        String request = proxy.request();
        System.out.println(request);
    }
}
