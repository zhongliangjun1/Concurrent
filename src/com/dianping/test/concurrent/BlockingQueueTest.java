package com.dianping.test.concurrent;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

/**
 * Hello world!
 * 
 */
public class BlockingQueueTest {
    public static void main(String[] args) throws Exception {
        BlockingQueue<Meal> queue = new LinkedBlockingDeque<Meal>();
        Waiter customer1 = new Waiter(queue, "yc");
        Waiter customer2 = new Waiter(queue, "gyl");
        Waiter customer3 = new Waiter(queue, "lm");
        Chef chef = new Chef(queue);
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(chef);
        executorService.execute(customer1);
        executorService.execute(customer2);
        executorService.execute(customer3);
       // TimeUnit.SECONDS.sleep(100);
       // executorService.shutdownNow();
    }
}

class Meal {
    int order;

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "Meal [order=" + order + "]";
    }

}

class Waiter implements Runnable {

    public Waiter(BlockingQueue<Meal> queue, String name) {
        super();
        this.queue = queue;
        this.name = name;
    }

    private String name;

    BlockingQueue<Meal> queue;

    public void run() {
        try {
            while (!Thread.interrupted()) {
                Meal meal = queue.take();
                System.out.println(name + " got meal:" + meal);
                TimeUnit.SECONDS.sleep(3);
            }
        } catch (InterruptedException e) {
            System.out.println("interrupted");
        }
    }

}

class Chef implements Runnable {

    private int order;

    BlockingQueue<Meal> queue;

    public Chef(BlockingQueue<Meal> queue) {
        super();
        this.queue = queue;
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                Meal meal = new Meal();
                meal.order = order++;
                queue.put(meal);
                System.out.println("chef produce meal:" + meal);
                TimeUnit.SECONDS.sleep(1);
            }
        } catch (InterruptedException e) {
            System.out.println("interrupted");
        }
    }
}
