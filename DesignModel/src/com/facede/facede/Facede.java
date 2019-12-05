package com.facede.facede;

/**
 * Created by songjian on 9/11/2018.
 */
public class Facede {

    private Juicer juicer;
    private CoverMachine coverMachine;
    private Waiter waiter;

    public Facede(){
        this.juicer=new Juicer();
        this.coverMachine= new CoverMachine();
        this.waiter= new Waiter();
    }

    public void orderJuice(){
        waiter.cut();
        waiter.addWater();
        waiter.toJuicer();
        juicer.start();
        waiter.toCover();
        coverMachine.cover();
        waiter.toGuset();
    }

}
