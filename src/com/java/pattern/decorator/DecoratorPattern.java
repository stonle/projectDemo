package com.java.pattern.decorator;

/**
 * Decorator:装饰者模式
 *
 * 意图：动态地给一个对象添加一些额外的职责。就增加功能来说，
 *       Decorator模式相比生成子类更为灵活。该模式以对客 户端透明的方式扩展对象的功能。
 * 参与者： 1. Component（被装饰对象的基类）
 *            定义一个对象接口，可以给这些对象动态地添加职责。
 *            2.ConcreteComponent（具体被装饰对象）
 *            定义一个对象，可以给这个对象添加一些职责。
 *            3.Decorator（装饰者抽象类）
 *            维持一个指向Component实例的引用，并定义一个与Component接口一致的接口。
 *            4.ConcreteDecorator（具体装饰者）
 *            具体的装饰对象，给内部持有的具体被装饰对象，增加具体的职责。
 *涉及角色
 *    （1）抽象组件:定义一个抽象接口，来规范准备附加功能的类
 *    （2）具体组件：将要被附加功能的类，实现抽象构件角色接口
 *    （3）抽象装饰者：持有对具体构件角色的引用并定义与抽象构件角色一致的接口
 *    （4）具体装饰：实现抽象装饰者角色，负责对具体构件添加额外功能。
 *要点：
 *    装饰者与被装饰者拥有共同的超类，继承的目的是继承类型，而不是行为
 *    实际上Java 的I/O API就是使用Decorator实现的。
 * Created by Administrator on 2017/11/8.
 */
public class DecoratorPattern {

    public static void main(String[] args) {
        Human person = new Person();
        Decorator decorator = new Decorator_two(new Decorator_first(
                new Decorator_zero(person)));
        decorator.wearClothes();
        decorator.walkToWhere();
    }
}

/**
 * 定义被装饰者
 */
interface Human{
    public void wearClothes();
    public void walkToWhere();
}

/**
 *  具体被装饰对象，被装饰者初始状态有些自己的装饰
 */
class Person implements  Human{
    @Override
    public void wearClothes() {
        // TODO Auto-generated method stub
        System.out.println("穿什么呢。。");
    }

    @Override
    public void walkToWhere() {
        // TODO Auto-generated method stub
        System.out.println("去哪里呢。。");
    }
}

/**
 * 抽象装饰者
 */
abstract class Decorator implements Human{
    private Human human;
    public Decorator(Human human) {
        this.human = human;
    }
    public void wearClothes() {
        human.wearClothes();
    }

    public void walkToWhere() {
        human.walkToWhere();
    }
}

/**
 * 具体装饰:
 * 下面定义三种装饰，这是第一个，第二个第三个功能依次细化，即装饰者的功能越来越多
 **/
class Decorator_zero extends Decorator{

    public Decorator_zero(Human human) {
        super(human);
    }
    public void goHome() {
        System.out.println("进房子。。");
    }

    public void findMap() {
        System.out.println("书房找找Map。。");
    }

    @Override
    public void wearClothes() {
        super.wearClothes();
        goHome();
    }

    @Override
    public void walkToWhere() {
        super.walkToWhere();
        findMap();
    }
}
class Decorator_first extends Decorator{

    public Decorator_first(Human human) {
        super(human);
    }

    public void goClothespress() {
        System.out.println("去衣柜找找看。。");
    }

    public void findPlaceOnMap() {
        System.out.println("在Map上找找。。");
    }
    @Override
    public void wearClothes() {
        super.wearClothes();
        goClothespress();
    }

    @Override
    public void walkToWhere() {
        super.walkToWhere();
        findPlaceOnMap();
    }
}

class Decorator_two extends Decorator{

    public Decorator_two(Human human) {
        super(human);
    }

    public void findClothes() {
        System.out.println("找到一件D&G。。");
    }

    public void findTheTarget() {
        System.out.println("在Map上找到神秘花园和城堡。。");
    }

    @Override
    public void wearClothes() {
        super.wearClothes();
        findClothes();
    }

    @Override
    public void walkToWhere() {
        super.walkToWhere();
        findTheTarget();
    }
}