package com.decorator.Main;

import com.decorator.component.Coffee;
import com.decorator.decoration.Decorator;
import com.decorator.decoration.Ice;
import com.decorator.decoration.Milk;
import com.decorator.decoration.Sugar;

/**
 * Created by songjian on 9/6/2018.
 */
public class Main {
    public static void main(String[] args) {
        Coffee coffee = new Coffee();
        coffee.getDescription();
        System.out.println("**********************");
        /**
         * 咖啡加牛奶进行装饰
         */
        Decorator milkCoffee = new Milk(coffee);
        milkCoffee.getDescription();

        System.out.println("**********************");
        /**
         * 咖啡加牛奶加糖加冰进行装饰
         */
        Decorator allCoffee = new Sugar(new Ice(new Milk(coffee)));
        allCoffee.getDescription();


    }
}
