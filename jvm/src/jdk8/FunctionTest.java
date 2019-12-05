package jdk8;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

/**
 * Created by songjian on 7/30/2018.
 */
public class FunctionTest {
    public static void main(String[] args) {
        forEach(Arrays.asList(1,2,3,4,5,new Apple()),i-> System.out.println(i));
    }

    public static <T> void forEach(List<T> list, Consumer<T> c){
        for (T t : list) {
            c.accept(t);
        }
    }
}
