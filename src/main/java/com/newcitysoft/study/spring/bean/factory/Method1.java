package com.newcitysoft.study.spring.bean.factory;

import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.ConstructorArgumentValues;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * 直接编码方式
 * 实现对象注册与依赖绑定
 *
 * @author lixin.tian@renren-inc.com
 * @date 2018/7/16 15:12
 */
public class Method1 {

    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        BeanFactory container = bindViaCode(beanFactory);
        FXNewsProvider newsProvider = (FXNewsProvider) container.getBean("djNewsProvider");
        newsProvider.getAndPersistNews();
    }

    public static BeanFactory bindViaCode(BeanDefinitionRegistry registry) {
        AbstractBeanDefinition newsProvider = new RootBeanDefinition(FXNewsProvider.class);
        AbstractBeanDefinition newsListener = new RootBeanDefinition(DowJonesNewsListener.class);
        AbstractBeanDefinition newsPersister = new RootBeanDefinition(DowJonesNewsPersister.class);
        // 将bean定义注册到容器中
        registry.registerBeanDefinition("djNewsProvider", newsProvider);
        registry.registerBeanDefinition("djListener", newsListener);
        registry.registerBeanDefinition("djPersister", newsPersister);
        // 指定依赖关系
        // 1.可以通过构造方法注入方式
        ConstructorArgumentValues argValues = new ConstructorArgumentValues();

        argValues.addIndexedArgumentValue(0, newsListener);
        argValues.addIndexedArgumentValue(1, newsPersister);

        newsProvider.setConstructorArgumentValues(argValues);
        // 2.或者通过setter方法注入方式
        MutablePropertyValues propertyValues = new MutablePropertyValues();

        propertyValues.addPropertyValue(new PropertyValue("listener", newsListener));
        propertyValues.addPropertyValue(new PropertyValue("persister", newsPersister));

        newsProvider.setPropertyValues(propertyValues);
        // 绑定完成
        return (BeanFactory) registry;
    }
}
