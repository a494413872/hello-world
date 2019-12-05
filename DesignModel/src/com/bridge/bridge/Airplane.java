package com.bridge.bridge;

/**
 * Created by songjian on 9/19/2018.
 */
public abstract class Airplane {
    private Producer producer;

    public abstract void desc();

    public void descProducer(){
       producer.desc();
    }
    public Airplane(Producer producer){
        this.producer=producer;
    }
}
