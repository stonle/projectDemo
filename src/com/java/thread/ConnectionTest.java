package com.java.thread;

import java.sql.Connection;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 测试简易数据库的链接
 * Created by Administrator on 2017/11/27.
 */
public class ConnectionTest {
    static ConnectionPool connectionPool = new ConnectionPool(10);
    // 保证所有ConnectionRuner能够同时开启
    static CountDownLatch  start = new CountDownLatch(1);
    //main 线程将会等待所有的connectionRunner结束后才能继续执行
    static CountDownLatch end;

    public static void main(String[] args) {
      //线程数量，可以修改
        int threadCount = 20;
        end = new CountDownLatch(threadCount);
        int count = 20;
        AtomicInteger got = new AtomicInteger();
        AtomicInteger notGot = new AtomicInteger();
        for(int i = 0;i<threadCount;i++){
           Thread thread = new Thread(new ConnectionRuner(count,got,notGot),"ConnectionRunnerThread");
           thread.start();
        }
        start.countDown();;
        try {
            end.await();
            System.out.println("total invoke: "+(threadCount*count));
            System.out.println("got connection: "+ got);
            System.out.println("notGot connection;" + notGot);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    static class ConnectionRuner implements Runnable{
        int count;
        AtomicInteger got;
        AtomicInteger notGot;
        public ConnectionRuner(int count,AtomicInteger got,AtomicInteger notGot){
            this.count = count;
            this.got = got;
            this.notGot = notGot;
        }
        @Override
        public void run() {
            try {
                start.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            while (count>0){
                //重线程池中获取连接，如果1000ms内无法获取到将会还回null；
                //分别统计连接获取的数量got和未获取到的数量notGOot
                try {
                    Connection connection = connectionPool.fetcjCpmmection(1000);
                    if(connection != null){
                        try{
                              connection.createStatement();
                              connection.commit();
                        }finally {
                            connectionPool.releaseConnection(connection);
                            got.incrementAndGet();
                        }
                    }else{
                        notGot.incrementAndGet();
                    }
                } catch (Exception e) {

                }finally {
                    count --;
                }
            }
            end.countDown();
        }
    }
}
