package com.model.model;

/**
 * Created by songjian on 9/11/2018.
 */
public abstract class Father {

    public void stepOne(){
        System.out.println("this is father step one");
    }

    public abstract void stepTTwo();

    public void stepThree(){
        System.out.println("this is father step three");
    }

    public void operation(){
        stepOne();
        stepTTwo();
        stepThree();
    }
}
