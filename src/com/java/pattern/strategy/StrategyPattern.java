package com.java.pattern.strategy;

/**
 * 行为模式之一
 * Strategy : 策略模式
 *         定义一系列的算法，把每个算法封装起来，并使得他们可以相互替换，
 *         让算法独立于使用它的客户而变化。
 *  定义：定义一系列算法，将每一个算法封装起来，并让它们可以相互替换；
 *        策略模式让算法独立于使用它的客户而变化
 * 三个角色：
 *  Context: 上下文环境类，持有抽象策略角色的引用
 *  Strategy: 抽象策略类，定义一系列抽象的算法策略
 *  ConcreteStrategy: 具体策略类，实现具体的算法策略
 *
 *
 * Created by Administrator on 2017/11/10.
 */
public class StrategyPattern {
}

interface Compute{
    String compute(int first,int second);
}

class Add implements  Compute{

    @Override
    public String compute(int first, int second) {
        return "输出结果"+first+"+"+second+"="+(first+second);
    }
}
class Sub implements Compute{

    @Override
    public String compute(int first, int second) {
         return "输出结果"+first+"-"+second+"="+(first-second);
    }
}

class Mul implements Compute{

    @Override
    public String compute(int first, int second) {
        return "输出结果"+first+"*"+second+"="+(first*second);
    }
}

class Div implements Compute{

    @Override
    public String compute(int first, int second) {
        return "输出结果"+first+"/"+second+ "="+(first/second);
    }
}

class Context{
    private Compute compute;
    public Context(){
        compute = new Add();
    }

    public void setCompute(Compute compute){
        this.compute = compute;
    }

    public void calc(int first,int second){
        System.out.println(compute.compute(first,second));
    }
}


//策略模式例子二


//Strategy: 抽象策略类，定义一系列抽象的算法策略

/**
 * 首先定一个策略接口，这是诸葛亮老人家给赵云的三个锦囊妙计的接口
 */
interface  IStrategy{
    //每个锦囊妙计都是一个可执行的算法
    public void operate();
}

//ConcreteStrategy: 具体策略类，实现具体的算法策略
class BackDoor implements IStrategy{
    @Override
    public void operate() {
        System.out.println("找乔国老帮忙，让吴国太给孙权施加压力");
    }
}
class GivenGreenLight implements IStrategy{
    @Override
    public void operate() {
        System.out.println("求吴国太开个绿灯,放行！");
    }
}
class BlockEnemy implements IStrategy{
    @Override
    public void operate() {
        System.out.println("孙夫人断后，挡住追兵");
    }
}
//Context: 上下文环境类，持有抽象策略角色的引用
class ContextS{
    //构造函数，你要使用那个妙计
    private IStrategy straegy;
    public ContextS(IStrategy strategy){
        this.straegy = strategy;
    }
    //使用计谋了，看我出招了
    public void operate(){
        this.straegy.operate();
    }
}
//策略模式的好处就是：体现了高内聚低耦合的特性呀


//spring中的策略模式
/**
 * 看一下Spring中策略模式的使用，以Spring实例化对象时使用的策略模式为例，该部分源码位于org.objenesis.strategy下，
 * 看名字就很直白，对象(obj)实例化(enesis).策略(strategy):
 */
/** 抽象策略接口：定义一个实例化指定类的最优策略*/


// http://blog.csdn.net/goskalrie/article/details/52472734
