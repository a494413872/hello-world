package com.factory.abstractFactory;

/**
 * Created by songjian on 9/6/2018.
 */
public class FactroyTwo implements Factroy {
    @Override
    public Product produce() {
        return new ProductTwo();
    }

    @Override
    public Goods produceGoods() {
        return new GoodsTwo();
    }
}
