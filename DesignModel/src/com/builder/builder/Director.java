package com.builder.builder;

/**
 * Created by songjian on 9/19/2018.
 */
public class Director {

    private Builder builder;

    public Director(){
        this.builder = new ConcreteBuilder();
        builder.buildPart1();
        builder.buildPart2();
        Product product = builder.returnProduct();
        product.desc();
    }


}
