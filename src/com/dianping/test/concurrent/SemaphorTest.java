/**
 * 
 */
package com.dianping.test.concurrent;

import java.util.concurrent.Semaphore;

/**
 * @author cong.yu
 *
 */
public class SemaphorTest {

    /**
     * @param args
     */
    public static void main(String[] args) {
       Semaphore semaphore=new Semaphore(5, true);
    }

}
