package com.wmh.thread;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by 周大侠
 * 2019-01-25 19:58
 */
public class Thread10 {

    public static void main(String[] args) {
        final Service service = new Service();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                    service.get();

            }
        };
        Runnable runnable1 = new Runnable() {
            @Override
            public void run() {
                service.put();

            }
        };
        Thread thread1 = new Thread(runnable);
        Thread thread2 = new Thread(runnable1);
        thread1.start();
        thread2.start();

    }


}
class Service{
    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    public void put(){
        lock.writeLock().lock();
        try {
            System.out.println("写进程进入");
            Thread.sleep(3000);
            System.out.println("写入" +  ":" );
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.writeLock().unlock();
        }
    }
    public void get(){
        lock.readLock().lock();
        try {
            System.out.println("读进程进入");
            Thread.sleep(3000);
            System.out.println("获取元素");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.readLock().unlock();
        }
    }



}
