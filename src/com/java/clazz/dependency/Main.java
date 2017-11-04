package com.java.clazz.dependency;

/**
 * Created by Administrator on 2017/11/4.
 *
 * 面向对象类与类的关系，Dependency：依赖关系
 *
 * 一般而言，依赖关系在Java语言中体现为局域变量、方法的形参，或者对静态方法的调用。
 *
 * 不持有对象的引用
 */
public class Main {
    public static void main(String[] args) {
        ProgramMonkey monkey = new ProgramMonkey();
        Code code = new Code();
        monkey.programAndroid(code);
        monkey.programIOS(code);
        monkey.programPHP(code);
    }
}
class Code {
    public void coding(String str) {
        System.out.println("Coding with "+ str +" !");
    }
}

class ProgramMonkey {
    public void programAndroid(Code code) {
        code.coding("Java/Android");
    }

    public void programIOS(Code code) {
        code.coding("OC/IOS");
    }

    public void programPHP(Code code) {
        code.coding("PHP");
    }
}
