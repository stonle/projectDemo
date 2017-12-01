package com.java.atomic;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by Administrator on 2017/11/24.
 */
public class AtomicDemo {
    public static void main(String[] args) {
        //AtomicIntegerArrayDemo.test1();
        //AtomicReferenceDemo.test();
        AtomicIntegerFieldUpdaterTest.test();
    }
}
//原子跟新基本类型
class AtomicIntegerDemo{
    public static void test(){
        //Atomic 原子更新基本类型，Atomic包提供了一下3个类
        /**
         * AtomicBoolean
         * AtomicInteger
         * AtomicLong
         */
        AtomicInteger atomicInteger = new AtomicInteger(3);
        //1、compareAndSet(int expect,int update) 方法测试
        //如果输入的值等于预期值，则以原子的方式将该值设置为输入的值
        System.out.println(atomicInteger.compareAndSet(3,5));
        System.out.println(atomicInteger.get());
        //2、int addAndGet(int delta):以原子的方式将输入的值与实例中的值相加，并返回结果
        System.out.println(atomicInteger.addAndGet(5));

        //3、int getAndIncrement();已原子的方法将当前值加1，注意：这里返回的是自增前的值
        System.out.println(atomicInteger.getAndIncrement());
        System.out.println(atomicInteger.get());

        //4、int getAndSet(int newValue)：已原子的方式设置为nweValue，并返回旧值
        System.out.println(atomicInteger.getAndSet(15));
    }
}
//原子更新数组

/**
 * Atomic包提供了以下4个类
 * AtomicIntegerArray
 * AtomicLongArray
 * AtomicReferenceArray
 *
 */
class AtomicIntegerArrayDemo{
   public static void test1(){
       int[] value = new int[]{1,2};
       AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(value);
       //1、int addAndGet(int i，int delta):已原子的方式将输入值与数组中索引i的元素想家
       System.out.println(atomicIntegerArray.addAndGet(0,3));
       //不改变原数组的值
       System.out.println(value[0]);

       //2、boolean compareAndSet(int i,int expect,int update);如果当前的值等于预期的值，则以原子的方式将数组位置i的元素设置成update值

       System.out.println(atomicIntegerArray.compareAndSet(0,4,5));
       System.out.println(atomicIntegerArray.get(0));
   }
}

//原子跟新引用类型
/**
 * Atomic包提供了一下3个类
 * AtomicReference:原子跟新引用类型
 * AtomicReferenceFieldUpdater:原子跟新引用类型字段
 * AtomicMarkableReference:原子跟新带有标记的引用类型
 */
class AtomicReferenceDemo{

    public static AtomicReference<User> atomicReference = new AtomicReference<>();
    public static void test(){
        User user = new User("kekey",10);
        atomicReference.set(user);
        User updateUser = new User("stone",30);
        System.out.println(atomicReference.compareAndSet(user,updateUser));
        System.out.println(atomicReference.get());
    }

   static class User{
        private String name;
        private int old;
        public User(String name,int old){
            this.name = name;
            this.old = old;
        }

       public String getName() {
           return name;
       }

       public int getOld() {
           return old;
       }

       @Override
       public String toString() {
           return "User{" +
                   "name='" + name + '\'' +
                   ", old=" + old +
                   '}';
       }
   }
}
//原子跟新字段类
/**
 * Atomic包下提供了3个进行原子字段更新
 * AtomicIntegerFieldUpdater
 * AtomicLomgFieldUpdater
 * AtomicStampedReference：原子跟新带有版本号的引用类型
 */
class AtomicIntegerFieldUpdaterTest{
    //创建原子跟新器，并设置需要更新的对象类和对象的属性
    private static AtomicIntegerFieldUpdater<Users> a = AtomicIntegerFieldUpdater.newUpdater(Users.class,"old");
    public static void test(){
        Users user = new Users("conan",22);
        System.out.println(a.getAndIncrement(user));
        System.out.println(a.get(user));
    }
    static class Users {
        private String name;
        public volatile int old;

        public Users(String name, int old) {
            this.name = name;
            this.old = old;
        }

        public String getName() {
            return name;
        }

        public int getOld() {
            return old;
        }

        @Override
        public String toString() {
            return "User{" +
                    "name='" + name + '\'' +
                    ", old=" + old +
                    '}';
        }
    }
}