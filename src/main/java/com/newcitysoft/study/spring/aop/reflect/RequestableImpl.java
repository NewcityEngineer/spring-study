package com.newcitysoft.study.spring.aop.reflect;

/**
 * @author lixin.tian@renren-inc.com
 * @date 2018/8/7 10:12
 */
public class RequestableImpl implements IRequestable {

    @Override
    public String request() {
        return "RequestableImpl...";
    }
}
