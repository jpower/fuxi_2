package com.wmh.thread;

/**
 * Created by 周大侠
 * 2019-01-24 22:56
 */
public class Thread05 {
    public static void main(String[] args) {
        User user = new User();
        InputThread inputThread = new InputThread(user);
        OutputThread outputThread = new OutputThread(user);
        inputThread.start();
        outputThread.start();
    }
}

class InputThread extends Thread {
    private User user;

    public InputThread(User user) {
        this.user = user;
    }

    @Override
    public void run() {
        int count = 0;
        while (true) {
            synchronized (user) {
                if(user.isFlag()){
                    try {
                        user.wait();
                    } catch (InterruptedException e) {
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
                user.notify();
                count = (count + 1) % 2;
            }
        }


    }
}

class OutputThread extends Thread {
    private User user;

    public OutputThread(User user) {
        this.user = user;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (user) {
                if(!user.isFlag()){
                    try {
                        user.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(user);
                user.notify();
                user.setFlag(false);
            }
        }
    }
}

class User {
    private String name;
    private int age;
    private boolean flag;

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
