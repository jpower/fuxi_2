package com.wmh.thread;

/**
 * Created by 周大侠
 * 2019-01-24 20:16
 */
public class Thread02 {
    public static void main(String[] args) {
        final Thread thread1 = new Thread("1") {
            @Override
            public void run() {
                try {
                    for (int i = 0; i < 50; i++) {
                        Thread.sleep(100);
                                                Thread.yield();
                        System.out.println(Thread.currentThread().getName() + ":" + i);
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Thread thread2 = new Thread("2") {
            @Override
            public void run() {
                try {
                    for (int i = 0; i < 51; i++) {
                        this.sleep(100);
                        System.out.println(Thread.currentThread().getName() + ":" + i);

                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        thread1.start();
        thread2.setPriority(10);
        thread2.start();
    }
}
