package com.java.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 通过缓存的实例说明读写锁的使用方式
 * Created by Administrator on 2017/11/29.
 */
public class Cache {
    static Map<String,Object> map = new HashMap<>();
    static ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
    static Lock r = rwl.readLock();
    static Lock w = rwl.writeLock();
    static volatile boolean update = false;

    //降级锁
    public void processDate(){
        r.lock();
        if(!update){
            //必须先释放读锁
            r.unlock();
            //降级锁重写锁获取到开始
            w.lock();
            try{
                if(!update){
                    update = true;
                }
                r.lock();
            }finally {
                w.unlock();
            }
        }
    }
    //获取一个key对应的value
    public static final Object get(String key){
        r.lock();
        try {
            return map.get(key);
        }finally {
            r.unlock();
        }
    }

    //设置key对应的value，并返回旧的value
    public static final Object put(String key,String value){
        w.lock();
        try{
            return map.put(key,value);
        }finally {
            w.unlock();
        }
    }
    //清空所有的内容
    public static final void clear(){
        w.lock();
        try{
            map.clear();
        }finally {
            w.unlock();
        }
    }
}
