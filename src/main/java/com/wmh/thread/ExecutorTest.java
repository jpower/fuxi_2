package com.wmh.thread;

import org.junit.Test;

import java.util.concurrent.*;

/**
 * Created by 周大侠
 * 2019-01-25 13:49
 */
public class ExecutorTest {
    @Test
    public void fun1() {
        ExecutorService executor = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            final int finalI = i;
            executor.execute(new Runnable() {
                @Override
                public void run() {

                    System.out.println(Thread.currentThread().getName() + "  " + finalI);

                }
            });

        }
    }

    @Test
    public void fun2() {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 10; i++) {
            final int finalI = i;
            executor.execute(new Runnable() {
                @Override
                public void run() {

                    System.out.println(Thread.currentThread().getName() + "  " + finalI);

                }
            });

        }
    }

    @Test
    public void fun3() {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(3);
        for (int i = 0; i < 10; i++) {
            final int finalI = i;
            executor.schedule(new Runnable() {
                @Override
                public void run() {

                    System.out.println(Thread.currentThread().getName() + "  " + finalI);

                }
            }, 2, TimeUnit.SECONDS);

        }
    }

    @Test
    public void fun4() {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 10; i++) {
            final int finalI = i;
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + " " + finalI);

                }
            });

        }
    }
    @Test
    public void fun5(){
        ThreadPoolExecutor executor = new ThreadPoolExecutor(0, 2,
                0L, TimeUnit.SECONDS,
                new LinkedBlockingQueue(2));
        Thread thread = new Thread() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        };
        executor.execute(thread);
        executor.execute(thread);
        executor.execute(thread);




    }


}
