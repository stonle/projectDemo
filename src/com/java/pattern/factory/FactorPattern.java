package com.java.pattern.factory;

/**
 * Created by Administrator on 2017/11/4.
 *
 * 设计模式之四：工厂方法模式
 *
 *  工厂方法模式：
 *  Product(抽象产品)：封装了各种产品对象的公有方法，比如这里的Human
 *  ConcreteProduct(具体产品)：抽象产品的具体化，比如这里的珍珠和椰果YellowHuman、WhiteHuman;
 *  Factory(工厂)：实现创建所有产品实例的内部逻辑，比如这里的HumanFactory ；
 *
 *
 *  抽象工厂模式有四个要素：
 *     工厂接口：工厂接口是工厂方法模式的核心，与调用者直接交互用来提供产品。在实际编程中，有时候也会使用一个抽象类来作为与调用者交互的接口，其本质上是一样的。
 *     工厂实现：工厂实现决定如何实例化产品，是实现扩展的途径，需要有多少种产品，就需要有多少个具体的工厂实现。
 *     产品接口：产品接口的主要目的是定义产品的规范，所有的产品实现都必须遵循产品接口定义的规范。产品接口是调用者最为关心的，产品接口定义的优劣直接决定了调用者代码的稳定性。同样，产品接口也可以用抽象类来代替，但要注意最好不要违反里氏替换原则。
 *     产品实现：实现产品接口的具体类，决定了产品在客户端中的具体行为。
 */
public class FactorPattern {
    public static void main(String[] args) {
        //女娲第一次造人，试验性质，少造点，火候不足，缺陷产品
        System.out.println("------------造出的第一批人是这样的：白人-----------------");
        SHumanFactory sHumanFactory = new HumanFactory();
        Human whiteHuman = sHumanFactory.createHuman(WhiteHuman.class);
        whiteHuman.cry();
        whiteHuman.laugh();
        whiteHuman.talk();
        //女娲第二次造人，火候加足点，然后又出了个次品，黑人
        System.out.println("\n\n------------造出的第二批人是这样的：黑人-----------------");
        Human blackHuman = sHumanFactory.createHuman(BlackHuman.class);
        blackHuman.cry();
        blackHuman.laugh();
        blackHuman.talk();
        //第三批人了，这次火候掌握的正好，黄色人种（不写黄人，免得引起歧义），备注：RB人不属于此列
        System.out.println("\n\n------------造出的第三批人是这样的：黄色人种-----------------");

        Human yellowHuman = sHumanFactory.createHuman(YellowHuman.class);
        yellowHuman.cry();
        yellowHuman.laugh();
        yellowHuman.talk();
    }
}

interface  Human{
    // 首先定义什么是人类
    // 人是愉快的，会笑的，本来是想用smile表示，想了一下laugh更合适，好长时间没有大笑了；
     void laugh();

    // 人类还会哭，代表痛苦
     void cry();

    // 人类会说话
     void talk();
}

class  YellowHuman  implements Human{

    public void cry() {
        System.out.println("黄色人种会哭");
    }

    public void laugh() {
        System.out.println("黄色人种会大笑，幸福呀！");
    }

    public void talk() {
        System.out.println("黄色人种会说话，一般说的都是双字节");
    }
}

class WhiteHuman  implements  Human{

    public void cry() {
        System.out.println("白色人种会哭");
    }

    public void laugh() {
        System.out.println("白色人种会大笑，侵略的笑声");
    }

    public void talk() {
        System.out.println("白色人种会说话，一般都是但是单字节！");
    }
}

class BlackHuman  implements Human{

    public void cry() {
        System.out.println("黑人会哭");
    }

    public void laugh() {
        System.out.println("黑人会笑");
    }

    public void talk() {
        System.out.println("黑人可以说话，一般人听不懂");
    }
}
abstract  class  SHumanFactory{
    // 定一个烤箱，泥巴塞进去，人就出来，这个太先进了
    abstract <T extends  Human> T createHuman(Class<T> tClass);
}

/**
 * 通过反射简洁生产过程
 *
 */
class HumanFactory extends  SHumanFactory{
    @Override
    public <T extends Human> T createHuman(Class<T> tClass) {

        Human human = null; // 定义一个类型的人类
        try {
            human = (Human) Class.forName(tClass.getName()).newInstance(); // 产生一个人种
        } catch (InstantiationException e) {// 你要是不说个人种颜色的话，没法烤，要白的黑，你说话了才好烤
            System.out.println("必须指定人种的颜色");
        } catch (IllegalAccessException e) { // 定义的人种有问题，那就烤不出来了，这是...
            System.out.println("人种定义错误！");
        } catch (ClassNotFoundException e) { // 你随便说个人种，我到哪里给你制造去？！
            System.out.println("混蛋，你指定的人种找不到！");
        }
        return (T)human;
    }

}



