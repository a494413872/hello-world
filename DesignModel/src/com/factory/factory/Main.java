package com.factory.factory;

/**
 * Created by songjian on 9/6/2018.
 */
public class Main {
    public static void main(String[] args) {
        Factroy factroy1 = new FactoryOne();
        Product product1 = factroy1.produce();
        product1.desc();
        Factroy factroy2 = new FactroyTwo();
        Product product2 = factroy2.produce();
        product2.desc();

    }
}
