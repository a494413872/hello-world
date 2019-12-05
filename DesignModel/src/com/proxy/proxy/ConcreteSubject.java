package com.proxy.proxy;

/**
 * Created by songjian on 9/19/2018.
 */
public class ConcreteSubject implements Subject {
    @Override
    public void doAction() {
        System.out.println("this is a concrete subject");
    }
}
