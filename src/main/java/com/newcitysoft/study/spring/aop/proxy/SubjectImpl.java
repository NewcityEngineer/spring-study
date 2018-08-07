package com.newcitysoft.study.spring.aop.proxy;

/**
 * @author lixin.tian@renren-inc.com
 * @date 2018/8/7 9:58
 */
public class SubjectImpl implements ISubject {

    @Override
    public String request() {
        System.out.println("SubjectImpl");
        return "SubjectImpl...";
    }
}
