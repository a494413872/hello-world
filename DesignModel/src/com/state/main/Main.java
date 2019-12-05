package com.state.main;

import com.state.state.ConcreteInstrument1;
import com.state.state.Context;

/**
 * Created by songjian on 9/18/2018.
 */
public class Main {
    public static void main(String[] args) {
        Context context = new Context();
        context.setInstrument(new ConcreteInstrument1());
        context.makeSound();
        context.makeSound();
    }
}
