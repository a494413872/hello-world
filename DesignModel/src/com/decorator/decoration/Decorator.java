package com.decorator.decoration;

import com.decorator.component.Component;

/**
 * Created by songjian on 9/6/2018.
 */
public abstract class Decorator implements Component {

    Component component;

    public Decorator(Component component){
        this.component=component;
    }
}
