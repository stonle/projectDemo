package com.java.pattern.state;

/**
 *
 * State :行为设计模式之状态模式
 * 定义：当一个对象的内在状态发生改变时允许改变其行为，这个对象看起来像是改变了它的类
 * 三个角色：
 * Context：上下文环境，定义客户感兴趣的接口，维护一个State子类的实例，该实例定义了对象的当前状态
 * State：抽象状态，定义一个接口以封装与 Context 的一个特定状态相关的行为。
 * ConcreteState：具体状态，实现抽象状态中定义的接口，从而达到不同状态下的不同行为。
 * Created by Administrator on 2017/11/15.
 */
public class StatePattern {
}
//抽象状态
interface State{
    public void doSomeThing();
}
//具体状态
class MorningState implements  State{

    @Override
    public void doSomeThing() {
        System.out.println("早上懒床");
    }
}
class AfternoonState implements State{

    @Override
    public void doSomeThing() {
        System.out.println("下午学习");
    }
}
class EveningState implements State{

    @Override
    public void doSomeThing() {
        System.out.println("晚上打球");
    }
}

//context类，设置state的方法
class Context{
    public void setState(State state){
        System.out.println("状态改变");
        state.doSomeThing();
    }
}
