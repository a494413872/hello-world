package com.thread.executor;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;

public class ModTest {

	public static void main(String[] args) {
		int[] arr = new int[20];
		for (int i = 0; i < 20; i++) {
             arr[i] = (int)(Math.random()*100);
        }
        System.out.println(Arrays.toString(arr));
		ModTest test = new ModTest();

        test.getsubset(arr, 100, 0);
     }

     LinkedList<Integer> list = new LinkedList<>();

     private int getsum(LinkedList<Integer> list) {
         int sum = 0;
         Iterator<Integer> iterator = list.iterator();
         while(iterator.hasNext()){
             sum += iterator.next();
         }
         return sum;
     }

     private void getsubset(int[] a, int m, int i) {
         while (i < a.length) {
             list.add(a[i]);
             if (getsum(list) == m) {
                 System.out.println(list);
             }
             i++;
             getsubset(a, m, i);
             list.remove(list.size() - 1);
         }
     }
	}


