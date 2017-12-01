package com.java.concurrent;

import jdk.nashorn.internal.ir.Block;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;

/**
 * BlockingQueue:java.util.concurrent 包里的 BlockingQueue 接口表示一个线程安放入和提取实例的队列
 * 一个线程往里边放，另外一个线程从里边取的一个 BlockingQueue。
 一个线程将会持续生产新对象并将其插入到队列之中，直到队列达到它所能容纳的临界点。也就是说，它是有限的。如果该阻塞队列到达了其临界点，
 负责生产的线程将会在往里边插入新对象时发生阻塞。它会一直处于阻塞之中，直到负责消费的线程从队列中拿走一个对象。
 负责消费的线程将会一直从该阻塞队列中拿出对象。如果消费线程尝试去从一个空的队列中提取对象的话，
 这个消费线程将会处于阻塞之中，直到一个生产线程把一个对象丢进队列。

 * 无法向一个 BlockingQueue 中插入 null。如果你试图插入 null，BlockingQueue 将会抛出一个 NullPointerException
 * 基于队列的数据结构，获取除开始或结束位置的其他对象的效率不会太高
 *
 * BlockingQueue接口：实现有
 *   ArrayBlockingQueue
 *   DelayQueue
 *   LinkedBlockingQueue
 *   PriorityBlockingQueue
 *   SynchronousQueue
 *   SynchronousQueue
 *
 * Created by Administrator on 2017/11/22.
 */
public class BlockingQueueDemo {
    public static void main(String[] args) throws Exception {
       /* BlockingQueue queue = new ArrayBlockingQueue(1024);
        Producer producer = new Producer(queue);
        Consumer consumer = new Consumer(queue);
        new Thread(producer).start();
        new Thread(consumer).start();
        Thread.sleep(4000);*/
       // DelayQueueTest delayQueueTest = new DelayQueueTest();
  /*      DelayQueue<DelayedElement> delayQueue = new DelayQueue<DelayedElement>();

        //生产者
        DelayQueueTest.producer(delayQueue);

        //消费者
        DelayQueueTest.consumer(delayQueue);

        while (true){
            try {
                TimeUnit.HOURS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }*/


  //测试3
        DelayQueueExample.delayQueueTest();
    }
}
//例子 1；
//Producer 类
class Producer  implements Runnable{
    protected BlockingQueue queue = null;
    public Producer(BlockingQueue queue){
        this.queue = queue;
    }
    @Override
    public void run() {
            try {
                queue.put("1");
                Thread.sleep(1000);
                queue.put("2");
                Thread.sleep(1000);
                queue.put("3");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
}
//Consumer
class Consumer implements Runnable{
   protected BlockingQueue queue = null;
   public Consumer(BlockingQueue queue){
       this.queue = queue;
   }
    @Override
    public void run() {
        try {
            System.out.println(queue.take());
            System.out.println(queue.take());
            System.out.println(queue.take());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

/*
  数组阻塞队列 ArrayBlockingQueue
  ArrayBlockingQueue 是一个有界的阻塞队列，其内部实现是将对象放到一个数组里。有界也就意味着，它不能够存储无限多数量的元素。它有一个同一时间能够存储元素数量的上限。你可以在对其初始化的时候设定这个上限，但之后就无法对这个上限进行修改了(译者注：因为它是基于数组实现的，也就具有数组的特性：一旦初始化，大小就无法修改)。
  ArrayBlockingQueue 内部以 FIFO(先进先出)的顺序对元素进行存储。队列中的头元素在所有元素之中是放入时间最久的那个，而尾元素则是最短的那个。
  BlockingQueue<String> queue = new ArrayBlockingQueue<String>(1024);
  queue.put("1");
  String string = queue.take();
 */

/*
  延迟队列: DelayQueue
  DelayQueue 对元素进行持有直到一个特定的延迟到期。注入其中的元素必须实现 java.util.concurrent.Delayed 接口，
 */
class DelayQueueExample{
    public static void delayQueueTest() throws Exception {
        BlockingQueue queue = new DelayQueue();
            Delayed element1 = new DelayedElementObject(1000);
        System.out.println(element1);
        queue.put(element1);
    /*    Delayed element2 = (Delayed) queue.take();
        System.out.println(element2);*/
        Thread.sleep(1100);
        System.out.println(queue.size());
        System.out.println((Delayed) queue.take());

    }
}
class DelayedElementObject implements Delayed{
    private final long delay; //延迟时间
    private final long expire;  //到期时间

    public DelayedElementObject(long delay){
        this.delay = delay;
        this.expire = System.currentTimeMillis() + delay;
    }
    @Override
    public long getDelay(TimeUnit unit) {
        //判断为0或者为负数
        return unit.convert(this.expire - System.currentTimeMillis(),TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        return (int) (this.getDelay(TimeUnit.MILLISECONDS) - o.getDelay(TimeUnit.MILLISECONDS));
    }
}

class DelayQueueTest{
    /**
     * 每100毫秒创建一个对象，放入延迟队列，延迟时间1秒
     * @param delayQueue
     */
    public static void producer(final DelayQueue<DelayedElement> delayQueue){
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    try {
                        TimeUnit.MILLISECONDS.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    DelayedElement element = new DelayedElement(1000,"test");
                    delayQueue.offer(element);
                }
            }
        }).start();

        /**
         * 每秒打印延迟队列中的对象个数
         */
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    try {
                        TimeUnit.MILLISECONDS.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("delayQueue size:"+delayQueue.size());
                }
            }
        }).start();
    }

    /**
     * 消费者，从延迟队列中获得数据,进行处理
     * @param delayQueue
     */
    public static void consumer(final DelayQueue<DelayedElement> delayQueue){
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    DelayedElement element = null;
                    try {
                        element =  delayQueue.take();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(System.currentTimeMillis()+"---"+element);
                }
            }
        }).start();
    }
}
class DelayedElement implements Delayed {

    private final long delay; //延迟时间
    private final long expire;  //到期时间
    private final String msg;   //数据
    private final long now; //创建时间

    public DelayedElement(long delay, String msg) {
        this.delay = delay;
        this.msg = msg;
        expire = System.currentTimeMillis() + delay;    //到期时间 = 当前时间+延迟时间
        now = System.currentTimeMillis();
    }

    /**
     * 需要实现的接口，获得延迟时间   用过期时间-当前时间
     * @param unit
     * @return
     */
    @Override
    public long getDelay(TimeUnit unit) {
        System.out.println(this.expire - System.currentTimeMillis());
        return unit.convert(this.expire - System.currentTimeMillis() , TimeUnit.MILLISECONDS);
    }

    /**
     * 用于延迟队列内部比较排序   当前时间的延迟时间 - 比较对象的延迟时间
     *
     * DelayQueue 将会在每个元素的 getDelay() 方法返回的值的时间段之后才释放掉该元素。
     * 如果返回的是 0 或者负值，延迟将被认为过期，
     * 该元素将会在 DelayQueue 的下一次 take  被调用的时候被释放掉。
     * @param o
     * @return
     */
    @Override
    public int compareTo(Delayed o) {
        return (int) (this.getDelay(TimeUnit.MILLISECONDS) -o.getDelay(TimeUnit.MILLISECONDS));
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("DelayedElement{");
        sb.append("delay=").append(delay);
        sb.append(", expire=").append(expire);
        sb.append(", msg='").append(msg).append('\'');
        sb.append(", now=").append(now);
        sb.append('}');
        return sb.toString();
    }
}

//链阻塞队列 LinkedBlockingQueue
/**
 * LinkedBlockingQueue 内部以一个链式结构(链接节点)对其元素进行存储。
 * 如果需要的话，这一链式结构可以选择一个上限。如果没有定义上限，将使用 Integer.MAX_VALUE 作为上限
 *
 * 以下是 LinkedBlockingQueue 的初始化和使用示例代码
 * lockingQueue<String> unbounded = new LinkedBlockingQueue<String>();
 * BlockingQueue<String> bounded   = new LinkedBlockingQueue<String>(1024);
 * bounded.put("Value");
 * String value = bounded.take();
 */
class  LinkedBlockingQueueDemo{
    public static void LinkedBlockingTest(){
        BlockingQueue<String> unbounded = new LinkedBlockingQueue<String>();
        BlockingQueue<String> bounded   = new LinkedBlockingQueue<String>(1024);
        try {
            bounded.put("string");
            String value = bounded.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}

// 具有优先级的阻塞队列 PriorityBlockingQueue
/**
 *
 * PriorityBlockingQueue 是一个无界的并发队列。
 * 它使用了和类 java.util.PriorityQueue 一样的排序规则。你无法向这个队列中插入 null 值。
 *
 * 所有插入到 PriorityBlockingQueue 的元素必须实现 java.lang.Comparable 接口。
 * 因此该队列中元素的排序就取决于你自己的 Comparable 实现。
 *
 * 同时注意，如果你从一个 PriorityBlockingQueue 获得一个 Iterator 的话，
 * 该 Iterator 并不能保证它对元素的遍历是以优先级为序的。
 **/
class PriorityBlockingQueueExcaple{
    public static void PriorityTest(){
        BlockingQueue queue = new PriorityBlockingQueue();
        try {
            queue.put("aaa");
            String value = (String) queue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}

// 同步队列 SynchronousQueue
/**
 SynchronousQueue 是一个特殊的队列，它的内部同时只能够容纳单个元素。如果该队列已有一元素的话，
 试图向队列中插入一个新元素的线程将会阻塞，直到另一个线程将该元素从队列中抽走。
 同样，如果该队列为空，试图向队列中抽取一个元素的线程将会阻塞，直到另一个线程向队列中插入了一条新的元素
 **/