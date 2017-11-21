package com.java.pattern.abstractFactory;

/**
 *
 *  抽象工厂模式：有四个要素：
 *     工厂接口：工厂接口是工厂方法模式的核心，与调用者直接交互用来提供产品。在实际编程中，有时候也会使用一个抽象类来作为与调用者交互的接口，其本质上是一样的。
 *     工厂实现：工厂实现决定如何实例化产品，是实现扩展的途径，需要有多少种产品，就需要有多少个具体的工厂实现。
 *     产品接口：产品接口的主要目的是定义产品的规范，所有的产品实现都必须遵循产品接口定义的规范。产品接口是调用者最为关心的，产品接口定义的优劣直接决定了调用者代码的稳定性。同样，产品接口也可以用抽象类来代替，但要注意最好不要违反里氏替换原则。
 *     产品实现：实现产品接口的具体类，决定了产品在客户端中的具体行为。
 * Created by Administrator on 2017/11/17.
 */
public class AbstractFactory {
}

//2个抽象产品
abstract class Drink{
    public abstract void drink();
}
abstract class Snack{
    public abstract void snack();
}
//具体产品
class MilkTea extends Drink{

    @Override
    public void drink() {
        System.out.println("一杯奶茶");
    }
}
class Juice extends Drink{

    @Override
    public void drink() {
        System.out.println("一杯果汁");
    }
}
class HandGrab extends Snack{

    @Override
    public void snack() {
        System.out.println("一个手抓饼");
    }
}
class FishBall extends Snack{

    @Override
    public void snack() {
        System.out.println("一碗鱼蛋");
    }
}

//抽象工厂
abstract  class MakeFood{
    abstract Drink createMakeDrink();
    abstract Snack createMakeSnack();
}
//具体工厂
class FirstXiaoDi extends MakeFood{

    @Override
    Drink createMakeDrink() {
        return new MilkTea();
    }

    @Override
    Snack createMakeSnack() {
        return new HandGrab();
    }
}
class SecondXiaoDi extends MakeFood{

    @Override
    Drink createMakeDrink() {
        return new Juice();
    }

    @Override
    Snack createMakeSnack() {
        return new FishBall();
    }
}