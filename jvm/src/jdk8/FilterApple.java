package jdk8;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * Created by songjian on 7/25/2018.
 */
public class FilterApple {
    public static List<Apple> filterGreeenApples(List<Apple> inventory){
        List<Apple> result = new ArrayList<Apple>();
        for (Apple apple : inventory) {
            if("green".equals(apple.getColor())){
                result.add(apple);
            }
        }
        return result;
    }
    public static List<Apple> filterGreeenApples(List<Apple> inventory, Predicate<Apple> p ){
        List<Apple> result = new ArrayList<Apple>();
        for (Apple apple : inventory) {
            if(p.test(apple)){
                result.add(apple);
            }
        }
        return result;
    }
    public static void main(String[] args) {
//        Apple apple = new Apple();
//        apple.setColor("green");
//        Apple apple2 = new Apple();
//        apple2.setColor("red");
//        Apple apple3 = new Apple();
//        apple3.setColor("red");
//        List<Apple> inventory = new ArrayList<Apple>();
//        inventory.add(apple);
//        inventory.add(apple2);
//        inventory.add(apple3);

//        List<Apple> result = filterGreeenApples(inventory);
//        List<Apple> result = filterGreeenApples(inventory,Apple::isGreenApple);
//        List<Apple> result = filterGreeenApples(inventory,(Apple a) -> "red".equals(a.getColor()));

//        System.out.println(result.size());
        Runnable r1 = ()-> System.out.println("hello word 1");
        Runnable r2 = new Runnable() {
            @Override
            public void run() {
                System.out.println("hello world 2");
            }
        };

        process(r1);
        process(r2);
        process(()->{
            System.out.println("hello world 3");
            System.out.println("hyell");
        });
        Thread t = new Thread(r1);
        t.start();

        say(()-> {System.out.println("say say say say");});
    }
    public static void say(MyInten inten){
        inten.sayHello();
    }
    public static void process(Runnable runnable){
        runnable.run();
    }
}
