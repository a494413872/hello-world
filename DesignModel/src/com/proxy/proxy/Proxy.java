package com.proxy.proxy;

/**
 * Created by songjian on 9/19/2018.
 */
public class Proxy implements Subject {

    private Subject subject;
    @Override
    public void doAction() {
        if(subject==null){
            this.subject = new ConcreteSubject();
        }
        subject.doAction();
    }
}
