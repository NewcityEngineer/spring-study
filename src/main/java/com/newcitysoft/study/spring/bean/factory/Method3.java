package com.newcitysoft.study.spring.bean.factory;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * 外部配置文件方式
 *
 * @author lixin.tian@renren-inc.com
 * @date 2018/7/16 15:34
 */
public class Method3 {
    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        BeanFactory container = bindViaPropertiesFile(beanFactory);
        FXNewsProvider newsProvider = (FXNewsProvider) container.getBean("djNewsProvider");
        newsProvider.getAndPersistNews();
    }

    public static BeanFactory bindViaPropertiesFile(BeanDefinitionRegistry registry) {
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(registry);
        reader.loadBeanDefinitions("classpath:config.xml");
        return (BeanFactory) registry;
    }
}
