package com.java.clazz.association;

/**
 * Created by Administrator on 2017/11/4.
 *   Association:关联关系   ；持有对象的引用
 *   使一个类知道另一个类的属性和方法。关联可以是双向的，也可以是单向的。在Java语言中，关联关系一般使用成员变量来实现
 */
public class Main {

    public static void main(String[] args) {
        ProgramMonkey monkey = new ProgramMonkey(new Code());
        Code code = new Code();
        monkey.programAndroid();
        monkey.programIOS(code);
    }

}

class Code {
    public void coding(String str) {
        System.out.println("Coding with "+ str +" !");
    }
}

class ProgramMonkey {
    private Code mCode;

    public ProgramMonkey(Code mCode) {
        this.mCode = mCode;
    }
    //成员变量关联
    public void programAndroid() {
        mCode.coding("Java/Android");
    }
    //形参关联
    public void programIOS(Code code) {
        code.coding("OC/IOS");
    }
}

