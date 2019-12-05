package jdk8;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class SimpleTest {
    private int a;
    private int b;
    private int c;
    public SimpleTest(int a,int b,int c){
        this.a=a;
        this.b=b;
        this.c=c;
    }

    public static void main(String[] args) {
        SimpleTest s1 = new SimpleTest(1,2,3);
        SimpleTest s2 = new SimpleTest(2,5,8);
        //第一遍
        Map<String,Object> map1 = new HashMap<>();
        int aa = s1.getA()-s2.getA();
        map1.put("desc",aa>0?"down":"up");
        map1.put("num",aa);
        //第二遍
        Map<String,Object> map2 = new HashMap<>();
        int bb = s1.getB()-s2.getB();
        map2.put("desc",bb>0?"down":"up");
        map2.put("num",bb);
        //第三遍
        Map<String,Object> map3 = new HashMap<>();
        int cc = s1.getC()-s2.getC();
        map3.put("desc",cc>0?"down":"up");
        map3.put("num",cc);


        Map<String,Object> map4 = getMap(s1,s2,SimpleTest::getA);
        Map<String,Object> map5 = getMap(s1,s2,SimpleTest::getB);
        Map<String,Object> map6 = getMap(s1,s2,SimpleTest::getC);

        System.out.println("map1"+map1);
        System.out.println("map2"+map2);
        System.out.println("map3"+map3);
        System.out.println("map4"+map4);
        System.out.println("map5"+map5);
        System.out.println("map6"+map6);

    }

    private static Map<String,Object>  getMap(SimpleTest s1, SimpleTest s2, Function<SimpleTest,Integer> function){
        int xx = function.apply(s1)-function.apply(s2);
        Map<String,Object> map = new HashMap<>();
        map.put("desc",xx>0?"down":"up");
        map.put("num",xx);
        return map;
    }


    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    public int getC() {
        return c;
    }

    public void setC(int c) {
        this.c = c;
    }
}
