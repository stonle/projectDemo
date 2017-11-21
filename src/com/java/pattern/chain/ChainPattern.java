package com.java.pattern.chain;

/**
 * 行为设计模式之一
 * Chain of Responsibility Pattern :责任链模式
 *       java设计模式之责任链模式、职责链模式
 * 定义：使多个对象都有机会处理请求，从而避免请求的发送者与接收者之间的耦合关系，
 * 将这个对象连成一条链，并沿着这条链传递该请求，知道有一个对象处理它为止。
 *
 * 两个角色
 *   Handler：抽象处理者，定义抽象请求处理方法，还定义一个抽象处理者对象作为
 *            其下家的引用，通过该引用，处理者可以连成一条链。
 *   ConcreteHandler：具体处理者，处理他所负责的请求，如果可处理就处理，不能
 *                    处理就把请求转发给下家。
 *
 * 责任链模式还分纯与不纯:
 *       纯责任链，要么承担全部责任，要么责任推个下家，不允许在某处承担了部分
 *    或者全部责任，然后又把责任推给下家。
 *  不纯责任链，责任在某处部分或全部被处理后，还向下传递。
 * Created by Administrator on 2017/11/14.
 */
public class ChainPattern {
    public static void main(String[] args) {
        //组装责任链
        Handler handler1 = new ConcreteHandler();
        Handler handler2 = new ConcreteHandler();
        handler1.setSuccessor(handler2);
        //提交请求
        handler1.handleequest();
    }
}

//抽象处理者角色
abstract class Handler{
    /**
     * 持有后续的责任对象
     */
    protected Handler successor;

    /**
     * 示意处理请求的方法，虽然这个示意方法是没有传入参数的
     * 但实际是可以传入参数的，根据具体需要来选择是否传递参数
     */
    public abstract void handleequest();

    /**
     * 取值方法
     */
    public Handler getSuccessor() {
        return successor;
    }
    /**
     * 赋值方法，设置后继的责任对象
     */
    public void setSuccessor(Handler successor) {
        this.successor = successor;
    }

}

//具体处理者角色
class ConcreteHandler  extends Handler{
    /**
     * 处理方法，调用此方法处理请求
     */
    @Override
    public void handleequest() {
        /**
         * 判断是否有后继的责任对象
         * 如果有，就转发请求给后继的责任对象
         * 如果没有，则处理请求
         */
        if(getSuccessor() != null)
        {
            System.out.println("放过请求");
            getSuccessor().handleequest();
        }else
        {
            System.out.println("处理请求");
        }
    }
}
