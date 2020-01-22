package jdk8.Stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamDemo {
    /**
        筛选用户

     */
    public static void main(String[] args) {
        User user = new User(1,"aa");
        User user2 = new User(2,"bb");
        User user3 = new User(3,"cc");
        User user4 = new User(4,"dd");
        User user5 = new User(5,"ee");
        User user6 = new User(6,"ff");
        User user7 = new User(7,"gg");
        /**
         * stream 流
         * 1.不保存数据
         * 2.不改变原来对象，返回一个新stream
         * 3.只要是函数式接口就可以用lamdba表达式
         */
        List<User> streamList = Arrays.asList(user,user2,user3,user4,user5,user6,user7);
        Stream<User> stream = streamList.stream();
        /**
         * 1.筛选出来id为偶数
         * 2.筛选出来id大于2
         * 3.名字转为大写
         * 4.用户名字母倒排序
         */
        stream.filter(u->{return u.getId()%2==0;}).filter(u->{return u.getId()>2;}).map(u->{return u.getName().toUpperCase();}).sorted((o1,o2)->{return o2.compareTo(o1);}).forEach(System.out::println);
        //映射map
        List<Integer> integers = Arrays.asList(1,2,3);
        List list2 = integers.stream().map(u->{return u*2;}).collect(Collectors.toList());
        for (Object o : list2) {
            System.out.println(o);
        }

    }
}
