package com.wmh.thread;

import javax.sound.midi.Soundbank;

/**
 * Created by 周大侠
 * 2019-01-24 18:37
 */
public class Thread01 {

    public static void main(String[] args) throws InterruptedException {
         Thread thread = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    try {
                        Thread.sleep(100);
                        System.out.println("子线程");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }
        };
         // 设置该线程为守护线程
        thread.setDaemon(true);
        thread.start();
//        thread.join();方法只会使主线程进入等待池并等待t线程执行完毕后才会被唤醒。
        for (int i = 0; i < 10; i++) {
            Thread.sleep(200);
            System.out.println("主线程");

        }

        System.out.println("主线程结束");
    }




}
