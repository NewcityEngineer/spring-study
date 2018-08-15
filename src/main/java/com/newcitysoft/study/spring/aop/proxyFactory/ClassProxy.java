package com.newcitysoft.study.spring.aop.proxyFactory;

import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.NameMatchMethodPointcutAdvisor;

/**
 * <pre>
 * spring aop有两种实现方式：基于接口的代理和基于类的代理
 *
 * 基于接口的代理默认使用jdk的动态代理机制
 * 基于类的代理使用cglib动态字节码生成技术
 *
 * ProxyFactory的核心接口：AopProxy
 * </pre>
 *
 * 基于类的代理
 *
 * @author lixin.tian@renren-inc.com
 * @date 2018/8/15 10:31
 */
public class ClassProxy {

    public static void main(String[] args) {
        // 1.创建目标对象
        Cat cat = new Cat();
        // 2.创建代理工厂对象（织入）
        ProxyFactory weaver = new ProxyFactory(cat);
        // 3.创建方面（含有PointCut和Advice）
        NameMatchMethodPointcutAdvisor advisor = new NameMatchMethodPointcutAdvisor();
        // 3.1 设置目标方法匹配
        advisor.setMappedName("eat");
        // 3.2 设置增强对象
        advisor.setAdvice(new PerformanceMethodInterceptor());
        // 4.织入器添加Aspect
        weaver.addAdvisor(advisor);
        // 5.获取代理对象
        Cat proxyObject = (Cat) weaver.getProxy();
        proxyObject.eat();
    }
}
