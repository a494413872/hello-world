package com.observer.Subject;

import com.observer.observer.Observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by songjian on 9/6/2018.
 */
public class Subject {
    private List<Observer> observers;
    public Subject(){
        observers = new ArrayList<Observer>();
    }

    public void attach(Observer observer){
        observers.add(observer);
    }

    public void detach(Observer observer){
        observers.remove(observer);
    }

    public void change(){
        String str = " subject has changed ";
        if(observers!=null){
            for (Observer observer : observers) {
                observer.sendMsg(str);
            }
        }
    }
}
