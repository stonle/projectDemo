package com.java.lock;

import org.junit.Test;

import java.util.concurrent.locks.Lock;

/**
 * Created by Administrator on 2017/11/28.
 */
public class TwinsLockTest {
    public static void main(String[] args) {
        final Lock lock = new TwinsLock();
        class Worker extends Thread{
            public void run(){
                for(;;){
                    lock.lock();
                    try {
                        Thread.sleep(1000);
                        System.out.println(Thread.currentThread().getName());
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }finally{
                        lock.unlock();
                    }
                }
            }
        }

        for(int i=0;i<10;i++){
            Worker worker = new Worker();
            worker.setDaemon(true);
            worker.start();
        }

        for(int i=0;i<10;i++){
            try {
                Thread.sleep(1000);
                System.out.println("----");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
