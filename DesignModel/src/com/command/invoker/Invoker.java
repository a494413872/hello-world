package com.command.invoker;

import com.command.command.Command;

/**
 * Created by songjian on 9/7/2018.
 */
public class Invoker {

    private Command command;

    public void setCommand(Command command){
        this.command=command;
    }

    public void invoke(){
        command.turnOn();
    }
}
