package com.bridge.bridge;

/**
 * Created by songjian on 9/19/2018.
 */
public class CargoAirplane extends Airplane {
    public CargoAirplane(Producer producer) {
        super(producer);
    }

    @Override
    public void desc() {
        System.out.print("this is cargo airpalen");
    }
}
