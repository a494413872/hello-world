package com.observer.Main;

import com.observer.Subject.Subject;
import com.observer.observer.Observer;
import com.observer.observer.ObserverOne;
import com.observer.observer.ObserverThree;
import com.observer.observer.ObserverTwo;

/**
 * Created by songjian on 9/6/2018.
 */
public class Main {
    public static void main(String[] args) {

        Subject subject = new Subject();
        Observer observerOne = new ObserverOne();
        Observer observerTwo = new ObserverTwo();
        Observer observerThree = new ObserverThree();
        /**
         * 先注册两个观察者，然后改变主题
         */
        subject.attach(observerOne);
        subject.attach(observerTwo);
        subject.change();
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@");
        /**
         * 移除掉观察者2，加入观察者3，然后再改变主题
         */
        subject.detach(observerTwo);
        subject.attach(observerThree);
        subject.change();


    }
}
