package com.java.clazz.aggregation;

/**
 * Created by Administrator on 2017/11/4.
 * Aggregation:聚合关系
 *
 *  聚合是关联关系的一种，是强的关联关系，聚合是整体和个体之间的关系。
 *  与关联关系一样，聚合关系也是通过实例变量实现的，
 *  但是关联关系所涉及的两个类是处在同一层次上的，而在聚合关系中，
 *  两个类是处在不平等层次上的，一个代表整体，另一个代表部分。
 *
 *
 *  本例的意思就是说写程序是Monkey的一项技能。聚合关系一般使用setter方法给成员变量赋值。
 */
public class Main {
    public static void main(String[] args) {
        ProgramMonkey monkey = new ProgramMonkey();
        monkey.setmCode(new Code());
        monkey.programAndroid();
    }
}
class Code {
    public void coding(String str) {
        System.out.println("Coding with "+ str +" !");
    }
}

class ProgramMonkey {
    private Code mCode;

    public Code getmCode() {
        return mCode;
    }

    public void setmCode(Code mCode) {
        this.mCode = mCode;
    }

    public ProgramMonkey() {
    }
    //也是聚合关系
    public void programAndroid() {
        mCode.coding("Java/Android");
    }
}
