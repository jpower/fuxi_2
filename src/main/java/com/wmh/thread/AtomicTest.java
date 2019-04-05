package com.wmh.thread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by 周大侠
 * 2019-01-25 21:06
 */
public class AtomicTest {
    public static void main(String[] args) {
        AtomicInteger integer = new AtomicInteger(100);
        System.out.println(integer.get());
        System.out.println(integer.incrementAndGet());
        System.out.println(integer.getAndAdd(100));
        System.out.println(integer.getAndDecrement());
        System.out.println(integer);
    }
}
