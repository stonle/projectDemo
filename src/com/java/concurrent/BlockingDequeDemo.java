package com.java.concurrent;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * BlockingDeque:阻塞双端队列
 *
 * BlockingDeque 类是一个双端队列，在不能够插入元素时，它将阻塞住试图插入元素的线程；在不能够抽取元素时，它将阻塞住试图抽取的线程。
 * deque(双端队列) 是 "Double Ended Queue" 的缩写。因此，双端队列是一个你可以从任意一端插入或者抽取元素的队列。
 * Created by Administrator on 2017/11/22.
 */
public class BlockingDequeDemo {
    public static void main(String[] args) throws InterruptedException {
        BlockingDequeDemo1.testBlockingDeque();
    }
}
/**
 * 一个 BlockingDeque - 线程在双端队列的两端都可以插入和提取元素。
 一个线程生产元素，并把它们插入到队列的任意一端。如果双端队列已满，
 插入线程将被阻塞，直到一个移除线程从该队列中移出了一个元素。
 如果双端队列为空，移除线程将被阻塞，直到一个插入线程向该队列插入了一个新元素。

 BlockingDeque 继承自 BlockingQueue
 */
class BlockingDequeDemo1{
    public static void testBlockingDeque() throws InterruptedException {
        BlockingDeque<String> deque = new LinkedBlockingDeque<String>();

        deque.addFirst("1");
        deque.addLast("2");

        String two = deque.takeLast();
        String one = deque.takeFirst();
        System.out.println(two);
        System.out.println(one);
    }
}