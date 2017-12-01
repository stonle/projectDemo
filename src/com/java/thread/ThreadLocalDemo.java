package com.java.thread;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.TimeUnit;

/**
 * ThreadLocal的使用
 * Created by Administrator on 2017/11/27.
 */
public class ThreadLocalDemo {

    //第一个get方法时会进行初始化(如果set方法没有调用)，每个线程会调用一次
    private static final ThreadLocal<Long> TIME_THREADLOCAL = new ThreadLocal<Long>(){
      protected Long initialValue(){
          return System.currentTimeMillis();
      }
    };

    public static final void begin(){
        TIME_THREADLOCAL.set(System.currentTimeMillis());
    }

    public static final long end(){
        return System.currentTimeMillis() - TIME_THREADLOCAL.get();
    }

    public static void main(String[] args) {
       /* begin();
        try {
            TimeUnit.SECONDS.sleep(1);
            System.out.println("Cost:"+ end() +" mills");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        TestNum.test();
    }
}
//ThreadLocal和线程同步机制都是为了解决多线程中相同变量的访问冲突问题
class TestNum{
    // ①通过匿名内部类覆盖ThreadLocal的initialValue()方法，指定初始值
    private static ThreadLocal<Integer> seqNum = new ThreadLocal<Integer>(){
        protected Integer initialValue(){
            return 0;
        }
    };
    //// ②获取下一个序列值

    public int getNexNum(){
        seqNum.set(seqNum.get() + 1);
        return seqNum.get();
    }

    public static void test(){
        TestNum sn = new TestNum();
        // ③ 3个线程共享sn，各自产生序列号
        TestClient t1 = new TestClient(sn);
        TestClient t2 = new TestClient(sn);
        TestClient t3 = new TestClient(sn);
        t1.start();
        t2.start();
        t3.start();
    }

    private static class TestClient extends Thread{
        private TestNum sn;
        public TestClient(TestNum sn){
            this.sn = sn;
        }
        @Override
        public void run() {
            for(int i = 0;i < 10;i++){
                //每个线程打出3个序列值
                System.out.println("thread[" + Thread.currentThread().getName() + "] --> sn["
                        + sn.getNexNum() + "]");
            }
        }
    }
}

//下面的实例能够体现Spring对有状态Bean的改造思路：
class TestDao{
    private Connection conn;// ①一个非线程安全的变量

    public void addTopic() throws SQLException {
        Statement stat = conn.createStatement();// ②引用非线程安全变量
        // …
    }
}
//由于①处的conn是成员变量，因为addTopic()方法是非线程安全的，必须在使用时创建一个新TopicDao实例（非singleton）。
// 下面使用ThreadLocal对conn这个非线程安全的“状态”进行改造：
//代码清单4 TestDao：线程安全
class ConnectionManager {

    private static ThreadLocal<Connection> connectionHolder = new ThreadLocal<Connection>() {
        @Override
        protected Connection initialValue() {
            Connection conn = null;
            try {
                conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/test", "username",
                        "password");
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return conn;
        }
    };

    public static Connection getConnection() {
        return connectionHolder.get();
    }

    public static void setConnection(Connection conn) {
        connectionHolder.set(conn);
    }
}