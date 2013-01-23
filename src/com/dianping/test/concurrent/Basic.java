/**
 * 
 */
package com.dianping.test.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author cong.yu
 * 
 */
public class Basic {
    public static void main(String[] args) throws InterruptedException,
            ExecutionException, TimeoutException {

        //new Thread(new Task("my task")).start();
         ExecutorService executorService = Executors.newCachedThreadPool();
        // executorService.execute(new Task("my task 1 "));
         
         //executorService.execute(new Task("my task 2 "));
        Future<String> future=executorService.submit(new CallableTask("my callable task"));
         String result=future.get(500, TimeUnit.MILLISECONDS);
        System.out.println(result);
        //System.out.println("main thread");
    }

}

class Task implements Runnable {

    private String name;

    private int counter;

    public Task(String name) {
        super();
        this.name = name;
    }

    public void run() {
        while (true) {
            System.out.println(name + " is doing the job:" + counter++);
            try {
                TimeUnit.SECONDS.sleep(1);
                // Thread.yield();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

}

class CallableTask implements Callable<String> {

    private String name;

    public CallableTask(String name) {
        super();
        this.name = name;
    }

    public String call() throws Exception {
        System.out.println(name + " is doing the job:");
        TimeUnit.SECONDS.sleep(1);
        return "done";
    }

}
