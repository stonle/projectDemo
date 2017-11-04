package com.java.clazz.realization;

/**
 * Created by Administrator on 2017/11/4.
 * Realization:实现关系
 * 一个类实现接口，重写相关方法，用 空心三角箭头的虚线表示
 */
public class Main {
    public static void main(String[] args) {
        ProgramMonkey monkey = new ProgramMonkey();
        monkey.run();
        monkey.code();
    }
}
interface CodeInterface {
    void code();
}

class ProgramMonkey implements CodeInterface {
    public void run() {
        System.out.println("I can run!");
    }

    @Override
    public void code() {
        System.out.println("I can coding!");
    }
}

