package com.wmh.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by 周大侠
 * 2019-01-25 16:59
 */
public class Thread09 {
    public static void main(String[] args) throws Exception {
        Callable callable = new Callable() {

            @Override
            public Object call() throws Exception {
                Thread.sleep(5000);
                return 3;
            }
        };
        ExecutorService service = Executors.newFixedThreadPool(3);
        Future submit = service.submit(callable);
        System.out.println("执行其他任务");

        System.out.println("sdfds");
        System.out.println("dsfsdg");
        if(service!=null){
            service.shutdown();
        }
    }


}
