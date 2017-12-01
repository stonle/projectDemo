package com.java.thread.ThreadPool;

/**
 * Created by Administrator on 2017/11/27.`
 */
public interface ThreadPool<Job extends Runnable> {
    //执行一个job，这个job需要实现Runnable
    void execute(Job  job);
    //关闭线程池
    void shotdown();
    //增加工作者线程
    void andWorkers(int num);
    //减少工作者线程
    void removeWorkers(int num);
    //得到正在等待执行的任务数量
    int getJobSize();
}
