package com.factory.abstractFactory;

/**
 * Created by songjian on 9/6/2018.
 */
public class FactoryOne implements Factroy {

    @Override
    public Product produce() {
        return new ProductOne();
    }

    @Override
    public Goods produceGoods() {
        return new GoodsOne();
    }


}
