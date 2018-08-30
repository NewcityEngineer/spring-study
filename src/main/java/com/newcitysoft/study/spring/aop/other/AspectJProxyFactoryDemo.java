package com.newcitysoft.study.spring.aop.other;

import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;

/**
 * 公开当前调用的代理对象
 * 使用案例
 * 参考自《Spring揭秘》 Chapter12
 *
 * @author lixin.tian@renren-inc.com
 * @date 2018/8/30 10:11
 */
public class AspectJProxyFactoryDemo {

    public static void main(String[] args) {
        AspectJProxyFactory weaver = new AspectJProxyFactory(new NestableInvocationBO());
        // 基于类的代理
        weaver.setProxyTargetClass(true);
        // java.lang.IllegalStateException: Cannot find current proxy: Set 'exposeProxy' property on Advised to 'true' to make it available.
        // 当目标对象依赖于代理对象是，需要设置exposeProxy为true，否则会报以上的错误。
        weaver.setExposeProxy(true);
        // 增加切面
        weaver.addAspect(PerformanceTraceAspect.class);
        // 获取代理对象
        Object proxy = weaver.getProxy();
        ((NestableInvocationBO) proxy).method2();
        ((NestableInvocationBO) proxy).method1();
    }
}
