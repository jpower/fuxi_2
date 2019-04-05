package com.wmh.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by 周大侠
 * 2019-01-24 22:56
 */
public class Thread06 {
    public static void main(String[] args) {
        User1 user = new User1();
        InputThread1 inputThread = new InputThread1(user);
        OutputThread1 outputThread = new OutputThread1(user);
        inputThread.start();
        outputThread.start();
    }
}

class InputThread1 extends Thread {
    private User1 user;

    public InputThread1(User1 user) {
        this.user = user;
    }

    @Override
    public void run() {
        int count = 0;
        while (true) {
            user.lock.lock();
            try {
                if (user.isFlag()) {
                    try {
                        user.condition.await();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                if (count == 0) {
                    user.setAge(18);
                    user.setName("wmh");
                } else {
                    user.setAge(80);
                    user.setName("wyy");
                }
                user.setFlag(true);
                user.condition.signal();
                count = (count + 1) % 2;
            } finally {
                user.lock.unlock();
            }
        }

    }
}

class OutputThread1 extends Thread {
    private User1 user;

    public OutputThread1(User1 user) {
        this.user = user;
    }

    @Override
    public void run() {
        while (true) {
            user.lock.lock();
            try {

                if (!user.isFlag()) {
                    try {
                        user.condition.await();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(user);
                user.condition.signal();
                user.setFlag(false);
            } finally {
                user.lock.unlock();
            }
        }
    }
}

class User1 {
    private String name;
    private int age;
    private boolean flag;
    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();
    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
