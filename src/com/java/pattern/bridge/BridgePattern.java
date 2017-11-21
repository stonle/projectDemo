package com.java.pattern.bridge;

import com.sun.org.apache.xpath.internal.SourceTree;

import java.util.Date;

/**
 * Bridge ：桥接模式
 *   把变化的因素都分离出来(抽象类)，让他独立变化(继承)，然后在程序运行时
 *动态地将抽象与实现部分组合起来。
 *
 * 定义：桥接模式基于单一职责原则，如果系统中的类存在多个
 * 变化的维度，通过该模式可以将这几个维度分离出来，
 * 然后进行独立扩展。这些分离开来的维度，通过在抽象层
 * 持有其他维度的引用来进行关联，就好像在两个维度间
 * 搭了桥一样，所以叫桥接模式
 *
 * Abstraction：抽象部分的接口。通常在这个对象里面，要维护一个实现部分
 * 的对象引用，在抽象对象里面的方法，需要调用实现部分的对象来完成。
 * 这个对象里面的方法，通常都是跟具体的业务相关的方法。
 *
 * Refined Abstraction：扩展抽象部分的接口，通常在这些对象里面，
 * 定义跟实际业务相关的方法，这些方法的实现通常会使用Abstraction中
 * 定义的方法，也可能需要调用实现部分的对象来完成。
 *
 * Implementor：定义实现部分的接口，这个接口不用和Abstraction
 * 里面的方法一致，通常是由Implementor接口提供基本的操作，而
 * Abstraction里面定义的是基于这些基本操作的业务方法，也就是说
 * Abstraction定义了基于这些基本操作的较高层次的操作。
 *
 * ConcreteImplementor：真正实现Implementor接口的对象。
 * Created by Administrator on 2017/11/8.
 */
public class BridgePattern {

    public static void main(String[] args) {
        System.out.println("\n"+new Date(System.currentTimeMillis()));
        System.out.println("========================");
        Steak steak1 = new BeefSteak(new Rice());
        System.out.println("卖出了一份："+steak1.sale());

        Steak steak2 = new PorkSteak(new Spaghetti());
        System.out.println("卖出了一份："+steak2.sale());

        Steak steak3 = new PorkSteak(null);
        System.out.println("卖出了一份："+steak3.sale());

        Steak steak4 = new ChickenSteak(new Rice());
        System.out.println("卖出了一份："+steak4.sale());

        Steak steak5 = new LambSteak(new Spaghetti());
        System.out.println("卖出了一份："+steak5.sale());
    }
}
//配餐类
abstract class  Rations{
    abstract String rations();
}
//继承配餐类的饭和意粉   ConcreteImplementor
class Rice extends  Rations{

    @Override
    String rations() {
        return "饭";
    }
}
class  Spaghetti extends  Rations{

    @Override
    String rations() {
        return "意粉";
    }
}

//扒类,,,  Abstraction
abstract  class  Steak{
    Rations rations;
    //关联关系，持有引用
    Steak(Rations rations){
        this.rations = rations;
    }

    public abstract String sale();
}

//牛扒与猪扒   Refined Abstraction：
class  BeefSteak extends Steak{
    BeefSteak(Rations rations){
        super(rations);
    }
    @Override
    public String sale() {
        return "牛扒"+(rations == null?"":rations.rations());
    }
}
class PorkSteak extends Steak{
    PorkSteak(Rations rations){
        super(rations);
    }

    @Override
    public String sale() {
        return "猪扒"+(rations == null?"":rations.rations());
    }
}

class ChickenSteak extends Steak{
    ChickenSteak(Rations rations){
        super(rations);
    }

    @Override
    public String sale() {
        return "鸡扒"+(rations == null?"":rations.rations());
    }
}

class LambSteak extends Steak{
    LambSteak(Rations rations){
        super(rations);
    }

    @Override
    public String sale() {
        return  "羊扒"+(rations == null?"":rations.rations());
    }
}
//酱汁
abstract class Sauces{
    Steak steak;
    Sauces(Steak steak){
        this.steak = steak;
    }

    abstract  String sauces();
}

class  HeiJiaoSauces extends Sauces{
    HeiJiaoSauces(Steak steak){
        super(steak);
    }

    @Override
    String sauces() {
        return steak.sale()+"加黑椒汁";
    }
}

class  XiangCaoSauces extends  Sauces{
    XiangCaoSauces(Steak steak){
        super(steak);
    }

    @Override
    String sauces() {
        return steak.sale()+"加香草汁";
    }
}