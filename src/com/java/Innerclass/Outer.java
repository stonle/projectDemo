package com.java.Innerclass;

/**
 * 内部类四种：常规内部类，静态内部类，局部内部类，匿名内部类
 * 常规内部类：
 * 会编译成2个 .class文件
 * Created by Administrator on 2017/12/1.
 */
public class Outer {
    private int x = 1;
    public Outer(){
        System.out.println("Outer initial");
    }
   public class Inner{
        //内部类对象中不能有静态成员，原因很简单，内部类的实例对象是外部类实例对象的一个成员
        // private static int y = 2;
        public Inner(){
            System.out.println("Inner initial");
        }
        private int x=2;

        public void add(){
            int x = 3;
            System.out.println(x);
            System.out.println(this.x);
            System.out.println(Outer.this.x);;
        }
   }

    public static void main(String[] args) {
        //常规内部类的初始化方法
        Inner inner = new Outer().new Inner();
        inner.add();
    }
}
