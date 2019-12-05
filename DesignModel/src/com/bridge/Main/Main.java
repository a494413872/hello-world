package com.bridge.Main;

import com.bridge.bridge.*;

/**
 * Created by songjian on 9/19/2018.
 */
public class Main {
    public static void main(String[] args) {
        Producer boeing = new Boeing();
        Airplane airplane = new PassengerAirPlane(boeing);
        airplane.desc();
        airplane.descProducer();


        Airplane airplane1 = new CargoAirplane(boeing);
        airplane1.desc();
        airplane1.descProducer();


    }
}
