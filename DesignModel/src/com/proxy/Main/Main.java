package com.proxy.Main;

import com.proxy.proxy.ConcreteSubject;
import com.proxy.proxy.Subject;

/**
 * Created by songjian on 9/19/2018.
 */
public class Main {
    public static void main(String[] args) {
        Subject subject = new ConcreteSubject();
        subject.doAction();
        //如果concreteSubject 在这里
    }
}
