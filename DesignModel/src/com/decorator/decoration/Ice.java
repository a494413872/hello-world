package com.decorator.decoration;

import com.decorator.component.Component;

/**
 * Created by songjian on 9/6/2018.
 */
public class Ice extends Decorator {

    public Ice(Component component) {
        super(component);
    }

    @Override
    public void getDescription() {
        super.component.getDescription();
        System.out.println("ice");
    }
}
