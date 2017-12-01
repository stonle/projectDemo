package com.java.concurrent;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Exchanger:线程间交换数据
 *  即为两线程可以交换彼此间的数据
 * Created by Administrator on 2017/11/24.
 */
public class ExchangerDemo {

    private static final Exchanger<String> exgr = new Exchanger<>();
    private static ExecutorService threadPool = Executors.newFixedThreadPool(2);

    public static void main(String[] args) {
        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                String A="银行流水A";
                try {
                    exgr.exchange(A);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                String B ="银行流水B";
                try {
                    String A = exgr.exchange(B);
                    System.out.println("A和B的数据是否一致："+A.equals(B)+",A录入的是："+A+",B录入的是:"+B);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        threadPool.shutdown();
    }

}
