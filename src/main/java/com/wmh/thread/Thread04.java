package com.wmh.thread;

/**
 * Created by 周大侠
 * 2019-01-24 20:53
 */
public class Thread04 implements Runnable{
    private  int  ticket = 100;
    @Override
    public void run() {
        while (ticket>0){
            try {
                Thread.sleep(50);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (this) {
                if(ticket>0) {
                    ticket--;
                    System.out.println(Thread.currentThread().getName() + ":" + (100 - ticket));

                }

            }
        }
    }

    public static void main(String[] args) {
        Thread04 thread04 = new Thread04();
        Thread thread1 = new Thread(thread04);
        Thread thread2 = new Thread(thread04);
        thread1.start();
        thread2.start();
    }
}
