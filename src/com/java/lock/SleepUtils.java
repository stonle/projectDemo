package com.java.lock;

import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2017/11/28.
 */
class SleepUtils{
    public static final void second(long seconds){
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}