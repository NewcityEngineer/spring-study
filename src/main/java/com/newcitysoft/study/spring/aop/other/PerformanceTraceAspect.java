package com.newcitysoft.study.spring.aop.other;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.util.StopWatch;

/**
 * @author lixin.tian@renren-inc.com
 * @date 2018/8/30 10:05
 */
@Aspect
@Slf4j
public class PerformanceTraceAspect {

    @Pointcut("execution(public void *.method1())")
    public void method1() {}

    @Pointcut("execution(public void *.method2())")
    public void method2() {}

    @Pointcut("method1() || method2()")
    public void compositePointcut() {}

    @Around(value = "compositePointcut()")
    public Object performanceTrace(ProceedingJoinPoint joinPoint) throws Throwable {

        StopWatch watch = new StopWatch();

        try {
            watch.start();
            return joinPoint.proceed();
        } finally {
            watch.stop();

            String logData = "PT in method[" + joinPoint.getSignature().getName() + "]>>>>>" + watch.toString();

            if(log.isInfoEnabled()) {
                log.info(logData);
            }
        }
    }
}
