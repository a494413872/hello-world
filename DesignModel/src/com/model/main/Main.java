package com.model.main;

import com.model.model.Father;
import com.model.model.sonOne;
import com.model.model.sonTwo;

/**
 * Created by songjian on 9/11/2018.
 */
public class Main {
    public static void main(String[] args) {
        Father f1 = new sonOne();
        Father f2 = new sonTwo();

        f1.operation();
        System.out.println("****************");
        f2.operation();
        Integer.parseInt("+123");
    }
}
