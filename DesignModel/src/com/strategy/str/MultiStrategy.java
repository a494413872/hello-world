package com.strategy.str;

/**
 * Created by songjian on 9/5/2018.
 */
public class MultiStrategy implements Strategy {
    @Override
    public int calculate(int i, int j) {
        return i*j;
    }
}
