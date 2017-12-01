package com.java.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 *  ReentrantLockDemo ：重入锁
 * Created by Administrator on 2017/11/23.
 *
 * 以上的对比说明线程在等待时(con.await)，已经不在拥有（keep）该锁了，所以其他线程就可以获得重入锁了。
 * 有必要会过头再看看Java官方的解释：“如果该锁定被另一个线程保持，则出于线程调度的目的，
 * 禁用当前线程，并且在获得锁定之前，该线程将一直处于休眠状态”。
 * 我对这里的“保持”的理解是指非wait状态外的所有状态，比如线程Sleep、for循环等一切有CPU参与的活动。
 * 旦线程进入wait状态后，它就不再keep这个锁了，其他线程就可以获得该锁；当该线程被唤醒（触发信号或者timeout）后，
 * 就接着执行，会重新“保持”锁，当然前提依然是其他线程已经不再“保持”了该重入锁。
 * 总结一句话：对于重入锁而言，"lock"和"keep"是两个不同的概念。lock了锁，不一定keep锁，但keep了锁一定已经lock了锁。
 */
public class ReentrantLockDemo {
    public static void main(String[] args) {

        final ExecutorService exec = Executors.newFixedThreadPool(4);

        final ReentrantLock lock = new ReentrantLock();

        final Condition con = lock.newCondition();

        final int time = 5;

        final Runnable add = new Runnable() {

            public void run() {

                System.out.println("Pre " + lock);

                lock.lock();

                try {

                    con.await(time, TimeUnit.SECONDS);

                } catch (InterruptedException e) {

                    e.printStackTrace();

                } finally {

                    System.out.println("Post " + lock.toString());

                    lock.unlock();

                }

            }

        };

        for(int index = 0; index < 4; index++)

            exec.submit(add);

        exec.shutdown();
    }
}
