package com.state.state;

/**
 * Created by songjian on 9/18/2018.
 */
public class ConcreteInstrument1 implements Instrument {
    @Override
    public void makeSound() {
        System.out.println("ding ding ding ");
    }
}
