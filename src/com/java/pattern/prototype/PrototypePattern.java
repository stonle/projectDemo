package com.java.pattern.prototype;

import java.io.*;

/**
 * Created by Administrator on 2017/11/4.
 *
 *   设计模式三、原型模式（Prototype Pattern）
 *   概述： 使用原型实例指定创建对象的种类，并且通过拷贝这些原型创建新的对象。
 *          简单的说就是对象的拷贝生成新的对象（对象的克隆），原型模式是一种对象创建型模式。
 *   使用场景：创建新的对象可以通过对已有对象进行复制来获得，如果是相似对象，则只需对其成员变量稍作修改。
 *
 *   参与者：(1)    Prototype（抽象原型类）：它是声明克隆方法的接口，是所有具体原型类的公共父类，
 *                  可以是抽象类也可以是接口，甚至还可以是具体实现类。
 *           (2)    ConcretePrototype（具体原型类）：它实现在抽象原型类中声明的克隆方法，
 *                    在克隆方法中返回自己的一个克隆对象。
 *           (3)    Client（客户类）：让一个原型对象克隆自身从而创建一个全新的对象。
 *
 *    注意：原型模式通过克隆方法所创建的对象是全新的对象，它们在内存中拥有新的地址，
 *    通常对克隆所产生的对象进行修改对原型对象不会造成任何影响，每一个克隆对象都是相互独立的。
 *
 *   扩展：关于浅克隆与深克隆的简单介绍
 *   (1) 在Java语言中，数据类型分为值类型（基本数据类型）和引用类型，值类型包括int、double、byte、boolean、char等简单数据类型，
 *       引用类型包括类、接口、数组等复杂类型。如下Person对象：
 *       注：浅克隆和深克隆的主要区别在于是否支持引用类型的成员变量的复制
 *
 *   (2)浅克隆
 *       在Java语言中，通过覆盖Object类的clone()方法就是实现浅克隆，在浅克隆中，当对象被复制时只复制它本身和
 *       其中包含的值类型的成员变量，而引用类型的成员对象并没有复制，也就是说原型对象只是将引用对象的地址复制一份给克隆对象，
 *       克隆对象和原型对象的引用类型成员变量还是指向相同的内存地址。
 *       注意：能够实现克隆的Java类必须实现一个标识接口Cloneable，表示这个Java类支持被复制。
 *       如果一个类没有实现这个接口但是调用了clone()方法，Java编译器将抛出一个CloneNotSupportedException异常。
 *    (3)深克隆：
 *        在深克隆中，无论原型对象的成员变量是值类型还是引用类型，都将复制一份给克隆对象，
 *        简单来说，在深克隆中，除了对象本身被复制外，对象所包含的所有成员变量也将复制。
 *        实现深clone:在Java语言中，如果需要实现深克隆，可以通过序列化(Serialization)等方式来实现。
 *        序列化就是将对象写到流的过程，写到流中的对象是原有对象的一个拷贝，而原对象仍然存在于内存中。
 *        通过序列化实现的拷贝不仅可以复制对象本身，而且可以复制其引用的成员对象，因此通过序列化将对象写到一个流中，
 *        再从流里将其读出来，可以实现深克隆。需要注意的是能够实现序列化的对象其类必须实现Serializable接口，
 *        否则无法实现序列化操作。
 *
 */

/**
 * 克隆必须满足3条件:
 *   1.对任何的对象x，都有：x.clone()!=x ，即不是同一对象、
 *   2.对任何的对象x，都有：x.clone().getClass==x.getClass()，即对象类型一致
 *   3.如果对象obj的equals()方法定义恰当的话，那么obj.clone().equals(obj)
 *     应当是成立的。(推荐，不强制)
 */
public class PrototypePattern {
    public static void main(String[] args) {
        Father father = new Father("老子", 50);
        Person son = new Person();
        son.setName("儿子");
        son.setAge(24);
        son.setFather(father);

        // 浅克隆出一个兄弟Person对象
        Person brother = son.clone();
        brother.getFather().setAge(60);
        System.out.println(brother == son);  // return false
        //比较的为地址
        System.out.println(brother.getFather() == son.getFather());  // return true
        System.out.println(brother.getFather().getAge());
        System.out.println(son.getFather().getAge());
        /**
         *  equals，不能用于比较基本数据类型，如果没对equals()方法进行
         *  重写，比较的是指向的对象地址，如果想要比较对象内容，需要自行重写
         *  方法，做相应的判断！！！！String调equals是可以判断内容是否一样，是
         *  因为对equals()方法进行了重写，具体自己参见源码！
         */
        System.out.println(brother.getFather().equals(son.getFather()));


        //深克隆测试
        DeepFather deepFather = new DeepFather("老子", 50);
        DeepPerson deepSon = new DeepPerson();
        deepSon.setName("儿子");
        deepSon.setAge(24);
        deepSon.setDeepFather(deepFather);

        try {
            DeepPerson deepBrother = deepSon.deepClone();
            System.out.println(brother == son);  // false
            System.out.println(brother.getFather() == son.getFather());  // false
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

abstract  class Prototype{
    /**
     * 提供抽象克隆方法
     */
    public abstract Prototype clone();
}

/**
 * 具体原型类A
 */
class  ConcretePrototypeA  extends  Prototype{

    /**
     * 浅克隆
     */
    @Override
    public Prototype clone() {
        Prototype prototype = new ConcretePrototypeA();
        return prototype;
    }
}


class Father{
    // 姓名
    private String name;
    // 年龄
    private int age;

    public Father(String name, int age) {
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
}

class Person implements Cloneable{
    // 姓名
    private String name;
    // 年龄
    private int age;
    // 他的父亲
    private Father father;

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
    protected Person clone(){
        Object obj = null;
        try {
            obj = super.clone();
            return (Person)obj;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Father getFather() {
        return father;
    }

    public void setFather(Father father) {
        this.father = father;
    }
}

class DeepFather implements Serializable{
    // 姓名
    private String name;
    // 年龄
    private int age;

    public DeepFather(String name, int age) {
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
}

class DeepPerson implements Serializable{
    // 姓名
    private String name;
    // 年龄
    private int age;
    // 他的父亲
    private DeepFather deepFather;
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

    public DeepFather getDeepFather() {
        return deepFather;
    }

    public void setDeepFather(DeepFather deepFather) {
        this.deepFather = deepFather;
    }

    /**
     * 深克隆
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     * @throws OptionalDataException
     */
    public DeepPerson deepClone() throws IOException, ClassNotFoundException, OptionalDataException
    {
        //将对象写入流中
        ByteArrayOutputStream bao=new  ByteArrayOutputStream();
        ObjectOutputStream oos=new  ObjectOutputStream(bao);
        oos.writeObject(this);

        //将对象从流中取出
        ByteArrayInputStream bis=new  ByteArrayInputStream(bao.toByteArray());
        ObjectInputStream ois=new  ObjectInputStream(bis);
        return  (DeepPerson) ois.readObject();
    }
}