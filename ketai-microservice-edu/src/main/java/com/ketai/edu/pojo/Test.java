package com.ketai.edu.pojo;

import java.util.*;

public class Test {
    public static void main(String[] args) {
        Map<Integer,String> map = new HashMap<>();
        Map<String,String>  table = new Hashtable<>();
        Set<String> sets = new HashSet();
   String cc = "";

        Set<String> tree = new TreeSet<>();


        for (int i = 0; i <5000 ; i++) {
            Thread t2 = new Thread(()->{
                for (int j = 0; j <1000 ; j++) {
                    map.put(j,"AAA");
                    System.out.println(Thread.currentThread().getName()+"A--->"+map);
                }
                System.out.println("Thread:A--->"+map);
            });
            t2.start();
        }
    }
}
