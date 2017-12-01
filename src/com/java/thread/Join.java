package com.java.thread;

import java.util.concurrent.TimeUnit;

/**
 * //Thread.join()使用
 * Created by Administrator on 2017/11/27.
 */
public class Join{
    public static void main(String[] args) {
        Thread thread = Thread.currentThread();
        for(int i = 0;i < 10;i++){
            //每个线程拥有一个线程的引用，需要等待前一个线程终止，才能重等待中返回
            Thread thread1 = new Thread(new Domino(thread,String.valueOf(i)));
            thread1.start();
            thread = thread1;
        }
        try {
            TimeUnit.SECONDS.sleep(2);
            System.out.println(Thread.currentThread().getName()+" terminate.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    static class Domino implements Runnable{
        private Thread thread;
        public Domino(Thread thread, String s){
            this.thread = thread;
        }
        @Override
        public void run() {
            try {
                thread.join();
                System.out.println(Thread.currentThread().getName()+"  terminate.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
