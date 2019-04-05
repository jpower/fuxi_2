package com.wmh.thread;

/**
 * Created by 周大侠
 * 2019-01-24 20:28
 */
public class Thread03 {
    public static void main(String[] args) throws InterruptedException {
        int a = 5;
        final Thread t1 = new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 20; i++) {
                    System.out.println("t1,i:" + i);
                }
            }
        });
        final Thread t2 = new Thread(new Runnable() {

            public void run() {
                for (int i = 0; i < 20000; i++) {
                    System.out.println("t2,i:" + i);
                }
            }
        });
        Thread t3 = new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 20; i++) {
                    System.out.println("t3,i:" + i);
                }
            }
        });

        t1.start();
        t1.join();

        t2.start();
        t2.join();

        t3.start();
    }
}

