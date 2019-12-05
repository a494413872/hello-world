package com.observer.observer;

/**
 * Created by songjian on 9/6/2018.
 */
public class ObserverOne implements Observer {

    @Override
    public void sendMsg(String str) {
        System.out.println("Observer one recieved message: "+str);
    }
}
