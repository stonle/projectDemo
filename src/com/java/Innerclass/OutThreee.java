package com.java.Innerclass;

/**
 * 静态内部类；以下生成2个字节码
 *
 *
 *  与一般内部类不同，在静态代码中不能够使用this操作，
 *  所以在静态内部类中只可以访问外部类的静态变量和静态方法。
 * Created by Administrator on 2017/12/1.
 */
public class OutThreee {
    static int x = 1;
    static class Nest{
        void print(){
            System.out.println(x);
        }
    }

    public static void main(String[] args) {
        OutThreee.Nest nest = new OutThreee.Nest();
        System.out.println(nest);
        OutThreee.Nest nest2 = new OutThreee.Nest();
        System.out.println(nest2);
    }
}
