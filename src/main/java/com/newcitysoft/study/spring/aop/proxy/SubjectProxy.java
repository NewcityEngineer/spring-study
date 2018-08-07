package com.newcitysoft.study.spring.aop.proxy;

/**
 * @author lixin.tian@renren-inc.com
 * @date 2018/8/7 9:59
 */
public class SubjectProxy implements ISubject {

    private ISubject subject;

    public SubjectProxy(ISubject subject) {
        this.subject = subject;
    }

    @Override
    public String request() {
        System.out.println(System.currentTimeMillis());
        return "Proxy:" + subject.request();
    }
}
