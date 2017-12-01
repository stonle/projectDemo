package com.java.thread.webServer;

import com.java.thread.ThreadPool.DefaultThreadPool;
import com.java.thread.ThreadPool.ThreadPool;
import com.sun.deploy.net.HttpRequest;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 一个基于线程池技术的简单web服务器
 * Created by Administrator on 2017/11/28.
 */
public class SimpleHttpServer {
    //处理HttpRequest的线程池
    static ThreadPool<HttpRequestHandler> threadPool = new DefaultThreadPool<>(1);
    //SimpleHttpServer的根路径
    static String basePath;
    static ServerSocket serverSocket;
    //服务器监听端口
    static  int port = 8080;
    public static void setPort(int port){
        if(port > 0){
            SimpleHttpServer.port = port;
        }
    }
    public static void setBasePath(String basePath){
        if(basePath!=null&&new File(basePath).exists()&& new File(basePath).isDirectory()){
            SimpleHttpServer.basePath = basePath;
        }
    }

    //启动SimpleHttpServer
    public static void start() throws IOException {
        serverSocket = new ServerSocket(port);
        Socket socket = null;
        while((socket = serverSocket.accept())!=null){
            //接收一个客户端的Socket，生成一个HttpRequestHandler，放入线程池
            threadPool.execute(new HttpRequestHandler(socket));
        }

        serverSocket.close();
    }
    static class HttpRequestHandler implements Runnable{
        private Socket socket;
        public HttpRequestHandler(Socket socket){
            this.socket = socket;
        }
        @Override
        public void run() {
            String line = null;
            BufferedReader br = null;
            BufferedReader reader = null;
            PrintWriter out = null;
            InputStream in = null;
            try {
                reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String header = reader.readLine();
                //由相对路径计算出绝对路径
                String filePath = basePath + header.split(" ")[1];
                out = new PrintWriter(socket.getOutputStream());
                //如果请求支援的后缀为jpg或者ico，则读取资源并输出
                if(filePath.endsWith("jpg") || filePath.endsWith("ico")){
                //以下省略......
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //关闭流或者Sicket
        private static void close(Closeable... closeables){
            if(closeables != null){
                for(Closeable closeable1:closeables){
                    try {
                        closeable1.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
