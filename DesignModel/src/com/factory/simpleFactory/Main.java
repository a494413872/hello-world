package com.factory.simpleFactory;

/**
 * Created by songjian on 9/6/2018.
 */
public class Main {
    public static void main(String[] args) {
        Product product1 = SimpleFactroy.produce("1");
        product1.desc();
        Product product2 = SimpleFactroy.produce("2");
        product2.desc();

    }
}
