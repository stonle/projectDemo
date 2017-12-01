package com.java.thread;

import java.sql.Connection;
import java.util.LinkedList;

/**
 * 一个简单的数据库连接实例
 * Created by Administrator on 2017/11/27.
 */
public class ConnectionPool {
    private LinkedList<Connection> pool = new LinkedList<>();
    public ConnectionPool(int initialSize){
        if(initialSize>0){
            for(int i = 0;i < initialSize;i++){
                pool.add(ConnectionDriver.createConnection());
            }
        }
    }

    public void releaseConnection(Connection connection){
        if(connection != null){
            synchronized (pool){
                //连接释放后需要进行通知，这样其它消费者能够感知到连接池中已经归还了一个连接
                pool.add(connection);
                pool.notifyAll();
            }
        }
    }

    //在mills内无法获得连接，将还回null
    public Connection fetcjCpmmection(long mills) throws InterruptedException {
        synchronized (pool){
            if( mills <= 0 ){
               while (pool.isEmpty()){
                   pool.wait();
               }
               return pool.removeFirst();
            } else {
              long future = System.currentTimeMillis()+mills;
              long remaining = mills;
              while(pool.isEmpty() && remaining > 0 ){
                  pool.wait();
                  remaining = future - System.currentTimeMillis();
              }
              Connection conn = null;
              if(!pool.isEmpty()){
                  conn = pool.removeFirst();
              }
              return conn;
            }
        }
    }
}
