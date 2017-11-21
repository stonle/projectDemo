package com.java.pattern.template;

/**
 * 行为设计模式之一
 * Template Method Pattern:模板方法模式
 *
 * 定义：定义一个操作中的算法的框架，而将一些步骤延迟到子类中，使得子类可以不改变
 *        一个算法的结构即可冲定义该算法的某些特定步骤。
 * 两个角色
 * AbstractClass：抽象模板，定义了模板结构，让子类去具体实现
 * ConcreteClass：具体模板，具体实现抽象类的抽象方法
 * Created by Administrator on 2017/11/14.
 */
public class TemplateMethodPattern {
}

// 抽象的模板方法类，这里我还加了一个钩子方法
abstract class Tea{
   protected void 加奶(){
       System.out.println("加入三花淡奶");
   }

   protected abstract void 加茶();

   protected abstract void 加料();

   protected void 打包(){
       System.out.println("用打包机打包");
   }

   //钩子方法
    protected boolean 是否打包(){
       return true;
    }

    public final void make(){
        System.out.println("===开始制作====");
        加奶();
        加茶();
        加料();
        if(是否打包()){
            打包();
        }
        System.out.println("===制作完毕===");
    }
}

// 具体实现类
class RedTeaMilkTea extends Tea{

    @Override
    protected void 加茶() {
        System.out.println("加入红茶");
    }

    @Override
    protected void 加料() {
        System.out.println("加入珍珠");
    }
}

class GreenTeaMilkTea extends Tea{

    private boolean isPack = true;

    public GreenTeaMilkTea(boolean isPack){
        this.isPack = isPack;
    }
    @Override
    protected void 加茶() {
        System.out.println("加入绿茶");
    }

    @Override
    protected void 加料() {
        System.out.println("加入椰果");
    }

    @Override
    protected boolean 是否打包(){
        return isPack;
    }
}