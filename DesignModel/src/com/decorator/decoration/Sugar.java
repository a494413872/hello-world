package com.decorator.decoration;

import com.decorator.component.Component;

/**
 * Created by songjian on 9/6/2018.
 */
public class Sugar extends Decorator {

    public Sugar(Component component) {
        super(component);
    }

    @Override
    public void getDescription() {
        super.component.getDescription();
        System.out.println("sugar");
    }
}
