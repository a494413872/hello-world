package com.observer.observer;


/**
 * Created by songjian on 9/6/2018.
 */
public class ObserverThree implements Observer {

    @Override
    public void sendMsg(String str) {
        System.out.println("Observer three recieved message: "+str);
    }
}
