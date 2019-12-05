package com.state.state;

/**
 * Created by songjian on 9/18/2018.
 */
public class Context {

    private Instrument instrument;


    public void makeSound(){
        instrument.makeSound();
        setInstrument(new ConcreteInstrument2());
    }


    public void setInstrument(Instrument instrument) {
        this.instrument = instrument;
    }
}
