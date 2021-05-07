package jdk8.Stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ListInList {
    public static void main(String[] args) {
        List<String> lista= Arrays.asList("a","b");
        List<String> listb= Arrays.asList("c","d");
        List<String> listc= Arrays.asList("1","2");
        List<String> listd= Arrays.asList("3","4");
        List<List<String>> listlist1 = new ArrayList<>();
        listlist1.add(lista);
        listlist1.add(listb);
        List<List<String>> listlist2 = new ArrayList<>();
        listlist2.add(listc);
        listlist2.add(listd);
        List<List<List<String>>> listlistlist = new ArrayList<>();
        listlistlist.add(listlist1);
        listlistlist.add(listlist2);
        List<String> ss=listlistlist.stream().flatMap(s->(s.stream().flatMap(b->b.stream()))).collect(Collectors.toList());
        System.out.println(ss);
    }
}
