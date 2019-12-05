package com.command.client;

import com.command.command.Command;
import com.command.command.ComputerComand;
import com.command.command.PhoneComand;
import com.command.invoker.Invoker;
import com.command.receiver.Computer;
import com.command.receiver.SmartPhone;

/**
 * Created by songjian on 9/7/2018.
 */
public class Main {
    //client角色，用来决定command和receiver之间的关系
    public static void main(String[] args) {

        Computer computer = new Computer();
        SmartPhone smartPhone = new SmartPhone();

        Command c1 = new ComputerComand(computer);
        Command c2 = new PhoneComand(smartPhone);
        //invoker来决定具体调用哪个command
        Invoker invoker = new Invoker();
        invoker.setCommand(c1);
        invoker.invoke();

        invoker.setCommand(c2);
        invoker.invoke();

    }
}
