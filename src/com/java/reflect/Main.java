package com.java.reflect;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

/**
 * Created by Administrator on 2017/11/18.
 */
public class Main {
    public static void main(String[] args) {
        //testClassLoader1();
        Main main = new Main();
        try {
            main.testGetResourceAsStream();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
    public static void testCalss(){
        // 反射机制获取类有三种方法
        Class clazz = null;
        //1 直接通过类名.Class的方式得到
        clazz = Person.class;
        System.out.println("通过类名: " + clazz);
        //2 通过对象的getClass()方法获取,这个使用的少（一般是传的是Object，不知道是什么类型的时候才用）
        Object obj = new Person();
        clazz = obj.getClass();
        System.out.println("通过getClass(): " + clazz);

        //3 通过全类名获取，用的比较多，但可能抛出ClassNotFoundException异常
        try {
            clazz = Class.forName("com.java.reflect.Person");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("通过全类名获取: " + clazz);

        //Class类的newInstance()方法，创建类的一个对象。
        //使用Class类的newInstance()方法创建类的一个对象
        //实际调用的类的那个 无参数的构造器（这就是为什么写的类的时候，要写一个无参数的构造器，就是给反射用的）
        //一般的，一个类若声明了带参数的构造器，也要声明一个无参数的构造器
        Object objs = null;
        try {
            objs = clazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        System.out.println(objs);
    }
    //ClassLoader类装载器
    public static void testClassLoader1(){
       //1、获取一个系统的类加载器
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        System.out.println("系统的类加载器-->" + classLoader);

        //2、获取系统类加载器的父类加载器(扩展类加载器（extensions classLoader）)
        classLoader = classLoader.getParent();
        System.out.println("扩展类加载器-->" + classLoader);
        //3、获取扩展类加载器的父类加载器
        //输出为Null,无法被Java程序直接引用
        classLoader = classLoader.getParent();
        System.out.println("启动类加载器-->" + classLoader);

        //4、测试当前类由哪个类加载器进行加载 ,结果就是系统的类加载器
        try {
            classLoader = Class.forName("com.java.reflect.Person").getClassLoader();
            System.out.println("当前类由哪个类加载器进行加载-->"+classLoader);
            //5、测试JDK提供的Object类由哪个类加载器负责加载的
            classLoader = Class.forName("java.lang.Object").getClassLoader();
            System.out.println("JDK提供的Object类由哪个类加载器加载-->" + classLoader);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 关于：Class.getResourceAsStream
     * 关于：ClassLoader.getResourceAsStream
     *       class.getResourceAsStream最终调用是ClassLoader.getResourceAsStream
     *        只是在这之前对参数进行了调整。如果参数已/开头，则去除/，否则把当前类的包名加在参数的前面。
     *        在使用ClassLoader.getResourceAsStream时，路径直接使用相对于classpath的绝对路径,并且不能已 / 开头。
     *         InputStream resourceAsStream = ClassLoader.getSystemResourceAsStream("com/github/demo/1.txt");
     * @throws UnsupportedEncodingException
     */
    //getResourceAsStream方法, Java绝对/相对路径获取与getResourceAsStream()方法
    public void testGetResourceAsStream() throws UnsupportedEncodingException {
        //5、关于类加载器的一个主要方法
        //调用getResourceAsStream 获取类路径下的文件对应的输入流
        InputStream in = this.getClass().getClassLoader()
                .getResourceAsStream("com/java/reflect/test.properties");
        System.out.println("in: " +in);

        Properties properties = new Properties();
        try {
            properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String driverClass = properties.getProperty("dirver");
        String jdbcUrl = properties.getProperty("jdbcUrl");
        //中文可能会出现乱码，需要转换一下
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");

        System.out.println("diverClass: "+driverClass);
        System.out.println("user: " + user);
    }
}

/**
 * 反射机制的作用
 * 在运行时判断任意一个对象所属的类；
 * 在运行时获取类的对象；
 * 在运行时访问java对象的属性，方法，构造方法等。
 */
class Person{
    String name;
    private int age;

    public Person() {
        System.out.println("无参构造器");
    }

    public Person(String name, int age) {
        System.out.println("有参构造器");
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Main{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
