package com.factory.simpleFactory;

/**
 * Created by songjian on 9/6/2018.
 */
public class SimpleFactroy {

    public static Product produce(String index){
        if("1".equals(index)){
            return new ProductOne();
        }else {
            return new ProductTwo();
        }

    }


}
