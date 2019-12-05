package com.strategy.cont;

import com.strategy.str.Strategy;

/**
 * Created by songjian on 9/5/2018.
 */
public class Context {
    private Strategy strategy;

    public Context(Strategy strategy){
        this.strategy=strategy;
    }

    public int excuteStrategy(int i,int j){
        return strategy.calculate(i,j);
    }

}
