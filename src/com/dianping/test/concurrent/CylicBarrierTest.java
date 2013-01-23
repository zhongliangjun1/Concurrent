/**
 * 
 */
package com.dianping.test.concurrent;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author cong.yu
 * 
 */
public class CylicBarrierTest {

    public static void main(String[] args) {
        CyclicBarrier barrier = new CyclicBarrier(3, new Runnable() {
            int count = 0;

            public void run() {
                System.out.println("all finished:" + count++);
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });
        Customer customer1 = new Customer(barrier, "1");
        Customer customer2 = new Customer(barrier, "2");
        Customer customer3 = new Customer(barrier, "3");
        //Customer customer4 = new Customer(barrier, "4");
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(customer1);
        executorService.execute(customer2);
        executorService.execute(customer3);
        //executorService.execute(customer4);
        // TimeUnit.SECONDS.sleep(1);
        // executorService.shutdownNow();
    }

}

class Customer implements Runnable {

    public Customer(CyclicBarrier b, String name) {
        super();
        this.barrier = b;
        this.name = name;
    }

    private String name;

    private static CyclicBarrier barrier;

    public void run() {
        try {
            while (true) {
                System.out.println("name:" + name + " finished");
                barrier.await();
            }
        } catch (InterruptedException e) {
            System.out.println("interrupted");
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}
