package com.wmh.thread;

class ThreadVolatileDemo {
     boolean flag = true;

    public  void runMethod() {
        while(flag) {
            synchronized(this){

            }
        }

        System.out.println("我停下来了");
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}

public class ThreadVolatile {
    public static void main(String[] args) throws InterruptedException {
        ThreadVolatileDemo threadVolatileDemo = new ThreadVolatileDemo();
        Thread thread = new Thread(() -> {
            threadVolatileDemo.runMethod();
        });
        Thread thread1 = new Thread(() -> {

            threadVolatileDemo.setFlag(false);
        });
        thread.start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        thread.join();
        thread1.start();
    }
}
