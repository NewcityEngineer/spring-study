package com.newcitysoft.study.spring.aop.proxyFactory;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.util.StopWatch;

/**
 * @author lixin.tian@renren-inc.com
 * @date 2018/8/15 10:26
 */
public class PerformanceMethodInterceptor implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        StopWatch watch = new StopWatch();
        try {
            watch.start();
            return methodInvocation.proceed();
        } finally {
            watch.stop();
            System.out.println(watch.toString());
        }
    }
}
