package com.command.command;

import com.command.receiver.SmartPhone;

/**
 * Created by songjian on 9/7/2018.
 */
public class PhoneComand implements Command {

    private SmartPhone smartPhone;

    public PhoneComand(SmartPhone smartPhone){
        this.smartPhone=smartPhone;
    }
    @Override
    public void turnOn() {
        smartPhone.powerOn();
        smartPhone.systemOn();
    }
}
