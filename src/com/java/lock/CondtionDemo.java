package com.java.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *  condition接口测试
 * Created by Administrator on 2017/11/29.
 */
public class CondtionDemo {
   Lock lock = new ReentrantLock();
   Condition condition = lock.newCondition();

   public void conditionWait() throws InterruptedException {
       lock.lock();
       try{
           condition.await();
       }finally {
           lock.unlock();
       }
   }

   public void conditionSignal(){
       lock.unlock();
       try{
           condition.signal();
       }finally {
           lock.unlock();
       }
   }
}
