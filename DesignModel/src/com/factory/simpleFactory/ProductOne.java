package com.factory.simpleFactory;

/**
 * Created by songjian on 9/6/2018.
 */
public class ProductOne implements Product {
    @Override
    public void desc() {
        System.out.println("this is product one");
    }
}
