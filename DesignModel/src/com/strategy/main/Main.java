package com.strategy.main;

import com.strategy.cont.Context;
import com.strategy.str.AddStrategy;
import com.strategy.str.MultiStrategy;
import com.strategy.str.SubStrategy;

import java.util.List;

/**
 * Created by songjian on 9/5/2018.
 */
public class Main {
    public static void main(String[] args) {
        Context add = new Context(new AddStrategy());
        System.out.println(add.excuteStrategy(1,2));

        Context sub = new Context(new SubStrategy());
        System.out.println(sub.excuteStrategy(1,2));

        Context multi = new Context(new MultiStrategy());
        System.out.println(multi.excuteStrategy(1,2));

    }
}
