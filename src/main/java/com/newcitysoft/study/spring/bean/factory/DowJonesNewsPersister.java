package com.newcitysoft.study.spring.bean.factory;

import org.springframework.stereotype.Component;

/**
 * @author lixin.tian@renren-inc.com
 * @date 2018/7/16 14:26
 */
@Component
public class DowJonesNewsPersister implements IFXNewsPersister {

    @Override
    public void persist() {
        System.out.println("道琼斯新闻持久化...");
    }
}
