package com.decorator.decoration;

import com.decorator.component.Component;

/**
 * Created by songjian on 9/6/2018.
 */
public class Milk extends Decorator {

    public Milk(Component component) {
        super(component);
    }

    @Override
    public void getDescription() {
        super.component.getDescription();
        System.out.println("milk");
    }
}
