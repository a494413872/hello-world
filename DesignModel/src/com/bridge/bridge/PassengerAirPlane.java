package com.bridge.bridge;

/**
 * Created by songjian on 9/19/2018.
 */
public class PassengerAirPlane extends Airplane {
    public PassengerAirPlane(Producer producer) {
        super(producer);
    }

    @Override
    public void desc() {
        System.out.print("this is passenger airpalne ");
    }
}
