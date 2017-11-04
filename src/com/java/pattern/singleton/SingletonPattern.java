package com.java.pattern.singleton;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/11/4.
 * 设计模式一、Singleton:单例模式
 *
 * 应用场景：当需要保证类在内存中的对象唯一性，可以使用单例模式
 *           不想创建多个实例浪费资源，或者避免多个实例由于多次调用
 *           而出现错误。一般写工具类，线程池，缓存，数据库等可以用到。
 * 设计思想:
 *    实现步骤：1、私有化该类的构造函数
 *              2、通过new在本类中创建一个本类对象
 *              3、定义一个公有的方法，将在该类中所创建的对象返回
 */
public class SingletonPattern {
    public static void main(String[] args) {
        //只调用了一次构造方法
        System.out.println(EeumSingleton.INSTANCE.getInstance());
        System.out.println(EeumSingleton.INSTANCE.getInstance());
        System.out.println(EeumSingleton.INSTANCE.getInstance());
    }
}
/**
 *   恶汉模式：
 *   优点：类加载的时候就完成实例化，避免线程同步问题
 *   缺点：由于在类加载的时候就进行了实例化，所以没有达到Lazy Loading(懒加载)
 *   的效果，即使我们没用到这个实例，但是他还是会加载，从而造成内存浪费(可以忽略，
 *   最常用的一种)。
 */
class  BadmashSingleton{
      private static BadmashSingleton singleton = new BadmashSingleton();
      private BadmashSingleton(){}
      public static BadmashSingleton getInstance() {
            return singleton;
      }
}
/**
 * 懒汉模式(线程不安全，不可用）
 *
 * 描述：尽管达到了懒加载，但是却存在线程安全问题，比如有两个线程，刚好都
    执行完if(singleton == null)可能会引发的线程安全问题，解决这个方法，我们可以对getInstance方法加锁
 */
class LazySingleton{
    private static LazySingleton singleton = null;
    private LazySingleton(){}
    public static LazySingleton getInstance(){
        if(singleton == null) {
            singleton = new LazySingleton();
        }
        return singleton;
    }
}
/**
 * 加锁
 * 懒汉模式(线程安全，但效率低，不推荐使用)
 *    缺点：效率低
 *    还有一种写法是：
 *       synchronized (LazySingleton1.class) { instance = new LazySingleton1(); }
  *    这种锁写法是线程不安全的
 */
class LazySingleton1{
    private static LazySingleton1 singleton = null;
    private LazySingleton1(){}
    public static synchronized LazySingleton1 getInstance(){
        if(singleton == null) {
            singleton = new LazySingleton1();
        }
        return singleton;
    }
}

/**
 *  懒汉模式双重校验锁
 *
 *  代码中进行了两次if检查，这样就可以保证线程安全，初始化一次后，
 *  后面再次访问时，if检查，直接return 实例化对象。volatile是1.5后
 *   引入的，volatile关键字会屏蔽Java虚拟机所做的一些代码优化，会导
 *   致系统运行效率降低，而更好的写法是使用静态内部类来实现单例！
 */
class LazySingleton2{
    private static volatile LazySingleton2 singleton = null;
    private  LazySingleton2(){}
    public static  LazySingleton2 getInstance(){
        if(singleton == null) {
            synchronized(LazySingleton2.class){
                if(singleton == null) {
                    singleton = new LazySingleton2();
                }
            }
        }
        return singleton;
    }
}

/**
 *   静态内部类实现单例模式
 *
 *   和饿汉式类似，两者都是通过类装载机制来保证初始化实例
 *   的时候只有一个线程，从而避免线程安全问题，饿汉式的
 *   OuterSingleton，就会实例化，而静态内部类这种，
 *   OuterSingleton，不会立即实例化，调用getInstance方法，
 *   InnerSingletonHolder，从而完成OuterSingleton的实例化。
 */
class OuterSingleton{
    public OuterSingleton(){}
    public static final OuterSingleton getInstance() {
        return InnerSingletonHolder.INSTANCE;
    }
   private static class InnerSingletonHolder{
       private static final OuterSingleton INSTANCE = new OuterSingleton();
   }
}

/**
 * 枚举实现单例模式 ----- 推荐使用。
 *   访问方式：EeumSingleton.INSTANCE.method();
 *    INSTANCE即为EeumSingleton类型的引用，得到它就可以调用
 *   枚举中的方法。既避免了线程安全问题，还能防止反序列化
 *    重新创建新的对象，但是失去了类的一些特性，没有延时加载，
 *
 */
class Singleton{
}
enum  EeumSingleton{
    INSTANCE;
    private Singleton instance;
    EeumSingleton() {
        instance = new Singleton();
        System.out.println("-----调用了构造方法------");
    }
    public Singleton getInstance() {
        return instance;
    }
}

/**
 * 使用容器实现单例模式
 *
 * 将多种单例类型注入到一个统一的管理类中，在使用时根据key获取对象
 * 对应类型的对象。这种方式使得我们可以管理多种类型的单例，并且在使
 * 用时可以通过统一的接口进行获取操作，降低了用户的使用成本，也对用
 * 户隐藏了具体实现，降低了耦合度。
 * 优点：保持类对象唯一性，对于频繁创建和销毁的对象可以提高性能。
 * 缺点：扩展困难，单例的方法无法生成子类对象，要扩展的话基本要重写这个类。
 */
class SingletonManager{
    private static Map<String,Object> objMap = new HashMap<String,Object>();
    private SingletonManager () { }
    public static void registerService(String key,Object instance) {
        if(!objMap.containsKey(key)) {
            objMap.put(key,instance);
        }
    }
    public static Object getService(String key) {
        return objMap.get(key);
    }
}