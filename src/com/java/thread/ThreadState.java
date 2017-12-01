package com.java.thread;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;
import java.util.concurrent.TimeUnit;

/**
 * 线程的状态：
 * new、runable、blocked、waiting、time_waiting、terminated
 * Created by Administrator on 2017/11/25.
 */
public class ThreadState {
    public static void main(String[] args) {
       // Daemon.test();
       // Interrupted.test();
       // WaitNotify.test();
       // Piped.test();
    }
}
class SleepUtils{
    public static final void second(long seconds){
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
//Daemon线程
//java虚拟机中不存在非Daemon线程，java虚拟机将会退出
//随着main方法的执行完毕而终止，java虚拟机中的所有Daemon线程都要立即终止
class Daemon{

    public static void test(){
        Thread thread = new Thread(new DaemonRunner(),"DaemonRunner");
        thread.setDaemon(true);
        thread.start();
    }
    static class DaemonRunner implements Runnable{
        @Override
        public void run() {
            try{
                System.out.println(1111);
                SleepUtils.second(10);
                System.out.println(2222);
            }finally {
                System.out.println("DaemnThread finally run.");
            }
        }
    }
}

//线程中断
class Interrupted{

    public static void test(){
        Thread sleepThread = new Thread(new SleepRunner(),"SleepThread");
        Thread busyTherad = new Thread(new BusyRunner(),"BusyThread");
        sleepThread.start();
        busyTherad.start();
        //休眠5秒，让2线程充分运行
        SleepUtils.second(5);
        sleepThread.interrupt();
        busyTherad.interrupt();
        System.out.println("SleepThread:"+sleepThread.isInterrupted());
        System.out.println("BusyThread:"+busyTherad.isInterrupted());
    }
    static class SleepRunner implements Runnable{

        @Override
        public void run() {
            while (true){
                SleepUtils.second(10);
            }
        }
    }

    static class BusyRunner implements Runnable{

        @Override
        public void run() {
            while (true){

            }
        }
    }
}
//通知等待机制
class WaitNotify{
    static boolean flag =  true;
    static Object lock = new Object();
    public static void test(){
        Thread waitThread = new Thread(new Wait());
        Thread nofityThread = new Thread(new Notify());
        waitThread.start();
        SleepUtils.second(2);
        nofityThread.start();
    }

    static class Wait implements Runnable{
        @Override
        public void run() {
            //加锁，拥有lock的Monitor
            synchronized (lock){
                while (flag){
                    System.out.println(11111);
                    try {
                        lock.wait();//返回，执行后续代码
                        System.out.println(44444);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(2222);
            }
        }
    }
    static class Notify implements Runnable{
        @Override
        public void run() {
           synchronized (lock){
               System.out.println(333333);
               //通知不会释放当前线程lock锁，直到当前线程释放了lock锁，
               lock.notify();
               flag = false;
               SleepUtils.second(5);
           }
        }
    }
}

//管道的输入和输出流
/**
 * 主要用于线程之间的传输，而传输的媒介为内存
 **/
class Piped{
    public static void test(){
        PipedWriter out = new PipedWriter();
        PipedReader in = new PipedReader();
        try {
            out.connect(in);
            Thread thread = new Thread(new Print(in),"PrintThr4ead");
            thread.start();
            int receive = 0;
            while ((receive = System.in.read()) != -1){
                out.write(receive);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    static class Print implements Runnable{
        private PipedReader in;
        public Print(PipedReader in){
            this.in = in;
        }
        @Override
        public void run() {
           int receive = 0;
            try {
                while((receive = in.read()) !=-1){
                    System.out.print((char) receive);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}


