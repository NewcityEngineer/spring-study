package com.newcitysoft.study.spring.aop.reflect;

/**
 * @author lixin.tian@renren-inc.com
 * @date 2018/8/7 10:11
 */
public class SubjectImpl implements ISubject {

    @Override
    public String request() {
        return "SubjectImpl...";
    }
}
