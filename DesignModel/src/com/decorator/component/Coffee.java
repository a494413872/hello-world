package com.decorator.component;

/**
 * Created by songjian on 9/6/2018.
 */
public class Coffee implements Component {

    @Override
    public void getDescription() {
        System.out.println("coffee");
    }
}
