package com.wmh.thread;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by 周大侠
 * 2019-01-25 11:37
 */
public class Thread08 {
    public static void main(String[] args) {
        LinkedBlockingQueue queue = new LinkedBlockingQueue();
        ProducerThread1 producerThread = new ProducerThread1(queue);
        ConsumerThread1 consumerThread = new ConsumerThread1(queue);
        producerThread.start();
        consumerThread.start();


    }


}

class ProducerThread1 extends Thread {
    private LinkedBlockingQueue queue;

    public ProducerThread1(LinkedBlockingQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1900);
                int i = (int) (Math.random() * 100);
                queue.offer(i,2,TimeUnit.SECONDS);
                System.out.println("发送！！！！！"+i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }
}

class ConsumerThread1 extends Thread {
    private LinkedBlockingQueue queue;

    public ConsumerThread1(LinkedBlockingQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Object message = queue.poll(2,TimeUnit.SECONDS);
                if(message!=null) {
                    System.out.println("收到！！！！！！！！:" + message);
                }else{
                    System.out.println("2秒之内沒收到消息");
                    return;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
