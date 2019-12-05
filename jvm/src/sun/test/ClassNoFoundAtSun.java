package sun.test;

/**
 * Created by songjian on 5/25/2018.
 */
public class ClassNoFoundAtSun {
    public static void main(String[] args) {
        System.out.println("I am at java home Lib boot strap  1112131");
//        System.out.println(System.getProperty("sun.boot.class.path"));
        System.out.println(System.getProperty("java.ext.dirs"));
//        System.out.println(System.getProperty("java.class.path"));
    }
}
