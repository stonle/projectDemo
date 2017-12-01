package com.java.Innerclass;

import org.omg.CORBA.PUBLIC_MEMBER;

/**
 *
 *  1\第一匿名内部类可以是个接口，这个没什么好奇怪的哈。
    2\第12行到第21行是一个语句，就是定义了一个对象，因此21行大括号后面有个分号。
    3\匿名内部类用 new Pet(){ … } 的方式把声明类的过程和创建类的实例的过程合二为一。
    4\匿名内部类可以是某个类的继承子类也可以是某个接口的实现类。
 * Created by Administrator on 2017/12/1.
 */
public class OutOne {
    public interface Pet{
        public void beFriendly();
        public void play();
    }

    public static void main(String[] args) {
        Pet pet = new Pet() {
            @Override
            public void beFriendly() {
                System.out.println(11111);
            }

            @Override
            public void play() {
                System.out.println(22222);
            }
        };
        pet.beFriendly();
        pet.play();
    }
}
