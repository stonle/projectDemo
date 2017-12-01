package com.java.Innerclass;

/**
 *
 * 局部内部类
 *  会编译成2个 .class文件
 * 1\局部内部类的地位和方法内的局部变量的位置类似，因此不能修饰局部变量的修饰符也不能修饰局部内部类
 *      譬如public、private、protected、static、transient等
   2\局部内部类只能在声明的方法内是可见的，因此定义局部内部类之后，想用的话就要在方法内直接实例化，
        记住这里顺序不能反了，一定是要先声明后使用，否则编译器会说找不到。
   3\局部内部类不能访问定义它的方法内的局部变量，除非这个变量被定义为final 。
 * Created by Administrator on 2017/12/1.
 */
public class OutTwo {
    int x = 1;
    public void doSomething(){
        final int y = 2;
        class Inner2{
            int x = 3;
            void print(){
                int x = 4;
                System.out.println(x);
                System.out.println(this.x);
                System.out.println(OutTwo.this.x);
                System.out.println(y);
            }
        }
        Inner2 inner = new Inner2();
        inner.print();
    }

    public static void main(String[] args) {
        OutTwo tow = new OutTwo();
        tow.doSomething();
    }
}
