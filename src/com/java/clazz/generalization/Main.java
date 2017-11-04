package com.java.clazz.generalization;

/**
 * Created by Administrator on 2017/11/4.
 * Generalization:继承和泛化关系
 * 一个是父类，一个是子类，用 空心三角箭头的实线 表示
 */
public class Main {
    public static void main(String[] args) {
        ProgramMonkey monkey = new ProgramMonkey();
        monkey.run();
        monkey.program();
    }
}
class Monkey {
    public void run() {
        System.out.println("I can run!");
    }
}

class ProgramMonkey extends Monkey{
    public void program() {
        System.out.println("I can Program!");
    }
}
