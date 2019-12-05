package jdk8;

/**
 * Created by songjian on 7/25/2018.
 */
public class Apple {

    private String color;


    public void setColor(String color) {
        this.color = color;
    }


    public String getColor() {
        return color;
    }

    public static boolean isGreenApple(Apple apple) {
        return "green".equals(apple.getColor());
    }
}
