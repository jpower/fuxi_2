package com.wmh.thread;

import org.junit.Test;

import java.sql.Time;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by 周大侠
 * 2019-01-25 11:00
 */
public class Thread07 {
    public static void main(String[] args) {
        ConcurrentLinkedQueue queue = new ConcurrentLinkedQueue();
        queue.add("我1");
        queue.add("我2");
        queue.add("我3");
        queue.add("我4");
        queue.add("我5");
        System.out.println(queue.peek());
        System.out.println(queue.peek());
        System.out.println(queue.size());
    }
    @Test
    public void fun1() throws InterruptedException {
        LinkedBlockingQueue queue = new LinkedBlockingQueue(5);
        queue.add("我1");
        queue.add("我2");
        queue.add("我3");
        queue.add("我4");
        queue.add("我5");
        queue.offer("我6",2, TimeUnit.SECONDS);
        for (int i = 0; i < 7; i++) {
            System.out.println(queue.poll(2, TimeUnit.SECONDS));
        }

    }


}
