package com.java.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程池的测试
 *   newCachedThreadPool：创建一个可缓存线程池，如果线程池长度超过处理需要，
 *                        可灵活回收空闲线程，若无可回收，则新建线程。
 *
 *   使用线程池的好处：
 *   1、降低资源消耗
 *   2、提高响应速度
 *   3、提高线程的课管理性
 * Created by Administrator on 2017/11/22.
 */
public class NewCachedThreadPoolDemo {
    public static void main(String[] args) {
       // ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    }
}
