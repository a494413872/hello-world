package com.singleton;

/**
 * Created by songjian on 9/6/2018.
 */
public class Singleton {

    private  static Singleton singleton;

    private Singleton(){

    }

    public static Singleton getInstance(){
        if(singleton==null){
            singleton=new Singleton();
        }
        return singleton;
    }
}
