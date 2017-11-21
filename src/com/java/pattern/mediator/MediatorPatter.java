package com.java.pattern.mediator;

/**
 * Mediator:中介者模式
 * Created by Administrator on 2017/11/13.
 *
 * 与外观模式和代理模式区分：
 *     外观模式：结构型:对子系统提供统一的接口，单向，所有请求都委托子系统完成，树型
 *     代理模式：结构型，引用代理对象的方式来访问目标对象，单向
 *     中介者：行为型，用一个中介对象来封装一系列同事对象的交互行为，双向，一对多星型
 * 定义：
 *     用一个中介对象来封装一系列的对象交互，使得各对象不需要显式的相互引用，
 * 从而使其耦合松散，而且可以独立的改变他们之间的交互。
 *
 *  四个角色
 *   Mediator：抽象中介者
 *             定义了同事对象到中介者对象的接口
 *   ConcreteMediator：具体中介者
 *              实现相关方法，需要知道所有具体同事类，并从具体同事接手信息，
 *              像具体同事对象发出命令。
 *   Colleague：抽象同事
 *              定义了中介者对象接口，它只知道中介者而不知道其他同事对象。
 *   ConcreteColleague：具体同事
 *              实现相关方法，只知道自己的行为，不了解其他具体同事的状况，
 *              但都认识中介者对象。
 */
public class MediatorPatter {
    public static void main(String[] args) {
        HouseMdiator houseMdiator = new HouseMdiator();
        Landlord landlord = new Landlord("包租婆",houseMdiator);
        Tenant tenant = new Tenant("小猪",houseMdiator);

        //为中介者传入同事对象
        houseMdiator.setLandlord(landlord);
        houseMdiator.setTenant(tenant);

        //调用
        landlord.contact("单间500一个月，有兴趣吗？");
        tenant.contact("热水器，空调，网线有吗？");
        landlord.contact("都有。");
        tenant.contact("好吧，我租了。");
    }
}

//抽象中介类
abstract class Mediator{
  abstract void contact(People people,String msg);
}
//抽象同事类，两个属性，姓名，还有一个中介类的引用，因为所有同事都知道中介
abstract  class  People{
     protected String name;
     protected Mediator mediator;//每个人都知道中介
     public People(String name,Mediator mediator){
         this.name = name;
         this.mediator = mediator;
     }
}
//具体同事类，这里有房东和房客

class Landlord extends People{

    public Landlord(String name, Mediator mediator) {
        super(name, mediator);
    }

    public void contact(String msg){
        mediator.contact(this,msg);
    }

    public void getMessage(String msg){
        System.out.println("【房东】"+name+":"+msg);
    }
}

class Tenant extends People{

    public Tenant(String name, Mediator mediator) {
        super(name, mediator);
    }
    public void contact(String msg){
        mediator.contact(this,msg);
    }
    public void getMessage(String msg){
        System.out.println("【房客】"+name+":"+msg);
    }
}
//具体中介类，因为中介者知道所有的同事

class HouseMdiator extends  Mediator{
    //中介者知道所有的同事
    private Landlord landlord;
    private Tenant tenant;

    public Landlord getLandlord() {
        return landlord;
    }

    public void setLandlord(Landlord landlord) {
        this.landlord = landlord;
    }

    public Tenant getTenant() {
        return tenant;
    }

    public void setTenant(Tenant tenant) {
        this.tenant = tenant;
    }

    @Override
    void contact(People people, String msg) {
        if(people == tenant){
           tenant.getMessage(msg);
        }else{
            landlord.getMessage(msg);
        }

    }
}