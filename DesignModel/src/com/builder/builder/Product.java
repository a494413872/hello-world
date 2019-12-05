package com.builder.builder;

/**
 * Created by songjian on 9/19/2018.
 */
public class Product {
    private  String key;
    private String value;

    public void setKey(String key) {
        this.key = key;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void desc(){
        System.out.println(key +" "+value);
    }
}
