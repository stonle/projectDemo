package com.java.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 自定义同步主键
 * Created by Administrator on 2017/11/28.
 */
public class TwinsLock implements Lock
{
    private final Sync sync = new Sync(2);
    private static final class Sync extends AbstractQueuedSynchronizer{
        Sync(int count){
            if(count <0){
                throw new IllegalArgumentException("count must larger than zero");
            }
            setState(count);
        }

        public int tryAcquireShared(int reduceCount){
            for(;;){
                int current = getState();
                int newCount = current - reduceCount;
                if(newCount < 0 || compareAndSetState(current, newCount)){
                    return newCount;
                }
            }
        }
        //重写2个方法
       /* public int tryAcquireShared(int reduceCount){
            //在同步状态变更时需要使用compareAndSet方法做原子性保障
            for(;;){
                int current = getState();
                int newCount = current - reduceCount;
                if(newCount<0 || compareAndSetState(current,newCount)){
                    return newCount;
                }
            }
        }*/
        //释放
 /*       public boolean tryReleaseShared(int returnCount){
            for(;;){
                int current = getState();
                int newCount = current + returnCount;
                if(compareAndSetState(current,newCount)){
                    return true;
                }
            }
        }*/

        public boolean tryReleaseShared(int returnCount){
            for(;;){
                int current = getState();
                int newCount = current+returnCount;
                if(compareAndSetState(current, newCount)){
                    return true;
                }
            }
        }

    }

    @Override
    public void lock() {
        sync.acquireShared(1);
    }
    @Override
    public void lockInterruptibly() throws InterruptedException {
        // TODO Auto-generated method stub
    }
    @Override
    public boolean tryLock() {
        // TODO Auto-generated method stub
        return false;
    }
    @Override
    public boolean tryLock(long time, TimeUnit unit)
            throws InterruptedException {
        // TODO Auto-generated method stub
        return false;
    }
    @Override
    public void unlock() {
        sync.tryReleaseShared(1);
    }
    @Override
    public Condition newCondition() {
        // TODO Auto-generated method stub
        return null;
    }
}
