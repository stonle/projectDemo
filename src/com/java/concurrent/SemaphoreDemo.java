package com.java.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Semaphore : 控制并发线程数的semaphore
 *   是用来控制同时访问特定资源的线程数量，它通过协调各个线程，已保证合理的使用公共资源
 * Created by Administrator on 2017/11/24.
 */
public class SemaphoreDemo {
    private static final int THREAD_COUNT = 30;
    //线程池用了必须要关闭
    private static ExecutorService threadPool = Executors.newFixedThreadPool(THREAD_COUNT);
    private static Semaphore semaphore = new Semaphore(10);
    public static void main(String[] args) {
        for(int i=0;i<THREAD_COUNT;i++){
            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        semaphore.acquire();
                        System.out.println("save data");
                        semaphore.release();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            });
        }
        threadPool.shutdown();
    }
}

