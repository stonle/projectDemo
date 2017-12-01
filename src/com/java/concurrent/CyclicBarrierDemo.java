package com.java.concurrent;

import java.util.Map;
import java.util.concurrent.*;

/**
 * CyclicBarrier 同步屏障
 * Created by Administrator on 2017/11/23.
 */
public class CyclicBarrierDemo {
    //参数表示屏障拦截的线程数量，每个线程调用await方法告诉CyclicBarrier我已经到达屏障，然后当前线程当前线程被阻塞
   public static  CyclicBarrier cyclicBarrier = new CyclicBarrier(2);

    public static void main(String[] args) {
        //new BanjWaterService().count();
       new  CyclicBarrierTest3().test2();
    }

    public static void test1(){

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
                System.out.println(1);
            }
        }).start();

        try {
            cyclicBarrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
        System.out.println(2);
    }
    public static void test2(){
      final CyclicBarrier cyclicBarrier1 = new CyclicBarrier(2,new A());
      new Thread(new Runnable() {
          @Override
          public void run() {
              try {
                  cyclicBarrier1.await();
              } catch (InterruptedException e) {
                  e.printStackTrace();
              } catch (BrokenBarrierException e) {
                  e.printStackTrace();
              }
              System.out.println(1);
          }
      }).start();
        try {
            cyclicBarrier1.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
        System.out.println(2);
    }
    static class A implements Runnable{

        @Override
        public void run() {
            System.out.println(3);
        }
    }
}
//银行流水处理服务类
class BanjWaterService implements Runnable{
    private  CyclicBarrier cyclicBarrier = new CyclicBarrier(4,this);

    private Executor executor = Executors.newFixedThreadPool(4);

    private Map<String,Integer> sheetMap = new ConcurrentHashMap<>();
    protected void count(){
        for(int i=0;i<4;i++){
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    sheetMap.put(Thread.currentThread().getName(),1);
                    try {
                        cyclicBarrier.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
    @Override
    public void run() {
        int result = 0;
        for(Map.Entry<String,Integer> sheet:sheetMap.entrySet()){
            result += sheet.getValue();
        }
        System.out.println(result);
    }
}
class CyclicBarrierTest3{
    static CyclicBarrier cyclicBarrier = new CyclicBarrier(2);
    public void test2(){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        thread.interrupt();;

        try {
            cyclicBarrier.await();
        } catch (Exception e) {
            System.out.println(cyclicBarrier.isBroken());
        }
    }
}

