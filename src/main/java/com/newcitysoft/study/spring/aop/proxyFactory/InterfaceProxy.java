package com.newcitysoft.study.spring.aop.proxyFactory;

import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.NameMatchMethodPointcutAdvisor;

/**
 * 基于接口的代理
 *
 * @author lixin.tian@renren-inc.com
 * @date 2018/8/15 10:24
 */
public class InterfaceProxy {

    public static void main(String[] args) {
        Dog dog = new Dog();
        ProxyFactory weaver = new ProxyFactory(dog);
        NameMatchMethodPointcutAdvisor advisor = new NameMatchMethodPointcutAdvisor();

        advisor.setMappedName("eat");
        advisor.setAdvice(new PerformanceMethodInterceptor());

        weaver.addAdvisor(advisor);

        Animal animal = (Animal) weaver.getProxy();
        animal.eat();
    }
}
