package com.java.concurrent;

import java.util.concurrent.CountDownLatch;

/**
 *  闭锁 CountDownLatch
 *
 *  CountDownLatch是一个同步工具类，它允许一个或多个线程一直等待，直到其他线程的操作执行完后再执行。
 *  在Java并发中，countdownlatch的概念是一个常见的面试题，所以一定要确保你很好的理解了它。
 * Created by Administrator on 2017/11/22.
 */
public class CountDownLatchDemo{
    public static void main(String[] args) {
        CountDownLatch1.testCountDownLatch();
    }
}
/**
 *  实现最大的并行性：有时我们想同时启动多个线程，实现最大程度的并行性。例如，我们想测试一个单例类。
 *  如果我们创建一个初始计数为1的CountDownLatch，并让所有线程都在这个锁上等待，那么我们可以很轻松地完成测试。
 *  我们只需调用 一次countDown()方法就可以让所有的等待线程同时恢复执行。
 *  开始执行前等待n个线程完成各自任务：例如应用程序启动类要确保在处理用户请求前，所有N个外部系统已经启动和运行了。
 *  死锁检测：一个非常方便的使用场景是，你可以使用n个线程访问共享资源，在每次测试阶段的线程数目是不同的，并尝试产生死锁。
 *
 *
 **/

/**
 * java中的并发工具类
 */
class CountDownLatch1{
    /**
     * join用法；用于让当前执行的线程等待join线程执行结束，其执行原理是不停的检查join线程是否存活，
     * 如果join线程存活则让当前线程永远等待，其中，wait(0)表示永远等待下去
     *
     * 实例代码：
     * while(isAlive()){
     *     wait(0);
     * }
     */
   public static void testJoin(){
       Thread parser1 = new Thread(new Runnable() {
           @Override
           public void run() {
               System.out.println("parser1 finish");
           }
       });
       Thread parser2 = new Thread(new Runnable() {
           @Override
           public void run() {
               System.out.println("parser2 finish");
           }
       });
       parser1.start();;
       parser2.start();
       try {
           parser1.join();
           parser2.join();

       } catch (InterruptedException e) {
           e.printStackTrace();
       }

       System.out.println("all parser finish");
   }
   static CountDownLatch countDownLatch = new CountDownLatch(2);
   public static void testCountDownLatch(){
       new Thread(new Runnable() {
           @Override
           public void run() {
               System.out.println(1);
               countDownLatch.countDown();
               System.out.println(2);
               countDownLatch.countDown();
           }
       }).start();
       try {
           countDownLatch.await();
       } catch (InterruptedException e) {
           e.printStackTrace();
       }
       System.out.println(3);
   }
}
