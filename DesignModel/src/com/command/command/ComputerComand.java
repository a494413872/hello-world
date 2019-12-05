package com.command.command;

import com.command.receiver.Computer;

/**
 * Created by songjian on 9/7/2018.
 */
public class ComputerComand implements Command {

    private Computer computer;

    public ComputerComand(Computer computer){
        this.computer=computer;
    }

    @Override
    public void turnOn() {
        computer.powerOn();
        computer.systemOn();

    }
}
