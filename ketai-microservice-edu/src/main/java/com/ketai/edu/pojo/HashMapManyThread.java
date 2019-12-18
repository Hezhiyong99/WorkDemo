package com.ketai.edu.pojo;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Yiron on 3/30 0030.
 */
public class HashMapManyThread {

    static Map<String,String > map =new HashMap<String, String>(16);//初始化容量

    public static  class TestHashMapThread implements Runnable{

        int start=0;

        public TestHashMapThread(int start){

            this.start=start;
        }

        @Override
        public void run() {

            for (int i = 0; i <100000 ; i+=2) {

                System.out.println("--puting----");

                map.put(Integer.toString(i),String.valueOf(Math.random()*100));
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {

        Thread[] threads =new Thread[100];

        for (int i = 0; i <threads.length ; i++) {

            threads[i]=new Thread(new TestHashMapThread(i));

        }

        for (int i = 0; i <100 ; i++) {

            threads[i].start();
        }

        System.out.println(map.size());
    }
}