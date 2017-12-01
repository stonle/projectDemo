package com.java.lock;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 重入锁
 * Created by Administrator on 2017/11/28.
 */
public class ReentrantLockDemo {

    private static Lock fairLock = new ReentrantLock2(true);
    private static Lock unfairLock = new ReentrantLock2(false);


    private static class Job extends Thread{
        private Lock lock;
        public Job(Lock lock){
            this.lock = lock;
        }
    }

    private static class ReentrantLock2 extends ReentrantLock{
        public ReentrantLock2(boolean fair){
            super(fair);
        }

        public Collection<Thread> getQueuedThreads(){
            List<Thread> arrayList = new ArrayList<>(super.getQueuedThreads());
            Collections.reverse(arrayList);
            return arrayList;
        }
    }

}
