package com.builder.builder;

/**
 * Created by songjian on 9/19/2018.
 */
public class ConcreteBuilder implements Builder{

    private Product product = new Product();

    @Override
    public void buildPart1() {
        product.setKey("hahaha");
    }

    @Override
    public void buildPart2() {
        product.setValue("dds");
    }

    @Override
    public Product returnProduct() {
       return this.product;
    }
}
