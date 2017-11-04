package com.java.clazz.composition;

/**
 * Created by Administrator on 2017/11/4.
 * Composition:组合关系
 *
 * 组合是关联关系的一种，是比聚合关系强的关系。
 * 它要求普通的聚合关系中代表整体的对象负责代表部分对象的生命周期，
 * 组合关系是不能共享的。代表整体的对象需要负责保持部分对象和存活，
 * 在一些情况下将负责代表部分的对象湮灭掉。
 * 代表整体的对象可以将代表部分的对象传递给另一个对象，由后者负责此对象的生命周期。
 * 换言之，代表部分的对象在每一个时刻只能与一个对象发生组合关系，
 * 由后者排他地负责生命周期。部分和整体的生命周期一样。
 *
 *
 * 所以说为了表示组合关系，常常会使用构造方法来达到初始化的目的。
 */
public class main {

    public static void main(String[] args) {
        ProgramMonkey monkey = new ProgramMonkey(new Code());
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

    public ProgramMonkey(Code mCode) {
        this.mCode = mCode;
    }

    public void programAndroid() {
        mCode.coding("Java/Android");
    }
}
