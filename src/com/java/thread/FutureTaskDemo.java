package com.java.thread;

import java.util.Map;
import java.util.concurrent.*;

/**
 *
 * Created by Administrator on 2017/11/23.
 */
public class FutureTaskDemo {

    private static final Map<Object,Future<String>> taskCache = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        try {
            System.out.println(executionTask("a"));
            System.out.println(executionTask("a"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static String executionTask(final String taskName) throws Exception{
        while(true){
            Future<String> future = taskCache.get(taskName);
            if(future == null){
                Callable<String> callable = new Callable<String>() {
                    @Override
                    public String call() throws Exception {
                        return taskName;
                    }
                };
                //1.2 创建任务
                FutureTask<String> futureTask = new FutureTask<String>(callable);
                future = taskCache.putIfAbsent(taskName,futureTask);
                if(future == null){
                    future = futureTask;
                    futureTask.run();
                }
            }
            try {
                return future.get();
            }catch (CancellationException e){
                taskCache.remove(taskName,future);
            }
        }
    }
}
