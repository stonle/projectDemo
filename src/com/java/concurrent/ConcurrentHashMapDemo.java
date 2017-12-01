package com.java.concurrent;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;


/**
 *  并发 Map(映射) ConcurrentMap
 *  1、java.util.concurrent - Java 并发工具包
 *  ConcurrentHashMap:ConcurrentHashMap在线程安全的基础上提供了更好的写并发能力，
 *                    但同时降低了对读一致性的要求（这点好像CAP理论啊 O(∩_∩)O）
 * Created by Administrator on 2017/11/22.
 */
public class ConcurrentHashMapDemo {
    public static void main(String[] args) {
        // api demo1 putIfAbsent的用法
        Map<String,String> map = new ConcurrentHashMap<>();
        //  如果不存在key对应的值，则将value以key加入Map，否则返回key对应的旧值
        String str = map.putIfAbsent("a","b");
        System.out.println(str);
        //若key已存在则不做操作，返回old值
        System.out.println(map.putIfAbsent("a","d"));
        System.out.println(map.get("a"));
    }
}

/**
 * V putIfAbsent(K key, V value)
 如果key对应的value不存在，则put进去，返回null。否则不put，返回已存在的value。

 boolean remove(Object key, Object value)
 如果key对应的值是value，则移除K-V，返回true。否则不移除，返回false。

 boolean replace(K key, V oldValue, V newValue)
 如果key对应的当前值是oldValue，则替换为newValue，返回true。否则不替换，返回false。
 */
//由于ConcurrentMap中不能保存value为null的值，所以需要处理不存在和已存在两种情况，不过可以使用AtomicInteger来替代
class demo2{
    public static void demo1() {
        final Map<String, AtomicInteger> count = new ConcurrentHashMap<>();
        final CountDownLatch endLatch = new CountDownLatch(6);
        Runnable task = new Runnable() {
            @Override
            public void run() {
                AtomicInteger oldValue;
                for (int i = 0; i < 5; i++) {
                    oldValue = count.get("a");
                    if (null == oldValue) {
                        AtomicInteger zeroValue = new AtomicInteger(0);
                        oldValue = count.putIfAbsent("a", zeroValue);
                        if (null == oldValue) {
                            oldValue = zeroValue;
                        }
                    }
                    oldValue.incrementAndGet();
                }
                endLatch.countDown();
            }
        };
        new Thread(task).start();
        new Thread(task).start();
        new Thread(task).start();
        new Thread(task).start();
        new Thread(task).start();
        new Thread(task).start();
        try {
            endLatch.await();
            System.out.println(count);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class demo1{
    public static void demo1() {
        final Map<String, Integer> count = new ConcurrentHashMap<>();
        final CountDownLatch endLatch = new CountDownLatch(2);
        Runnable task = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    Integer value = count.get("a");
                    if (null == value) {
                        count.put("a", 1);
                    } else {
                        count.put("a", value + 1);
                    }
                }
                endLatch.countDown();
            }
        };
        new Thread(task).start();
        new Thread(task).start();
        try {
            endLatch.await();
            System.out.println(count);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}