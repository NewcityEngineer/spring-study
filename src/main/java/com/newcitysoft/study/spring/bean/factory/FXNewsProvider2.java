package com.newcitysoft.study.spring.bean.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author lixin.tian@renren-inc.com
 * @date 2018/7/16 14:19
 */
@Component
public class FXNewsProvider2 {

    @Autowired
    private IFXNewsListener listener;
    @Autowired
    private IFXNewsPersister persister;

    public void getAndPersistNews() {
        System.out.println("FXNewsProvider...");
        persister.persist();
    }
}
