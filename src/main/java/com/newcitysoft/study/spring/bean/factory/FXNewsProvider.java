package com.newcitysoft.study.spring.bean.factory;

/**
 * @author lixin.tian@renren-inc.com
 * @date 2018/7/16 14:19
 */
public class FXNewsProvider {

    private IFXNewsListener listener;
    private IFXNewsPersister persister;

    public FXNewsProvider(IFXNewsListener listener, IFXNewsPersister persister) {
        this.listener = listener;
        this.persister = persister;
    }

    public IFXNewsListener getListener() {
        return listener;
    }

    public void setListener(IFXNewsListener listener) {
        this.listener = listener;
    }

    public IFXNewsPersister getPersister() {
        return persister;
    }

    public void setPersister(IFXNewsPersister persister) {
        this.persister = persister;
    }

    public void getAndPersistNews() {
        System.out.println("FXNewsProvider...");
        persister.persist();
    }
}
