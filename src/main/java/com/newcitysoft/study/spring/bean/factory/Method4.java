package com.newcitysoft.study.spring.bean.factory;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 *
 * @author lixin.tian@renren-inc.com
 * @date 2018/7/16 15:52
 */
public class Method4 {

    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("config2.xml");
        FXNewsProvider2 newsProvider = (FXNewsProvider2) ctx.getBean("FXNewsProvider2");
        newsProvider.getAndPersistNews();
    }

}
