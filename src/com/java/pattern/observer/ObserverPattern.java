package com.java.pattern.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * 行为模式之二
 * Observer : 观察者模式
 *
 * 四个角色
 * Subject：抽象被观察者，把所有观察者对象的引用保存到集合中，然后
 *  提供添加，移除，和通知观察者对象更新的方法。
 * ConcreteSubject：被观察者，集合存放观察者，重写增删和通知观察者
 *   的方法，当发生变化时通知观察者更新。
 * Observer：抽象观察者，定义一个更新接口，给被观察者更新的时候调
 *(ConcreteObserver：具体观察者，继承抽象观察者，实现具体的更新方法
 *
 * 观察者模式的推与拉
 *  推方式:
 *        被观察者对象向观察者推送主题的详细信息，不管观察者是否需要，
 *        推送的信息通常是被观察者对象的全部或部分数据。
 *  拉方式
 *      被观察者对象再通知观察者时，只传递少量信息。如果观察者需要更
 *      详细的信息，可以主动到被观察者中获取，相当于观察者从被观察者
 *      中拉取数据。一般的套路是：把主题对象自身通过update()方法传递
 *      给观察者，然后观察者在需要获取的时候，通过这个引用来获取。
 * Created by Administrator on 2017/11/10.
 */
public class ObserverPattern {
    public static void main(String[] args) {
        Plant flower = new Flower();

        Insect bee1 = new Bee(1);
        Insect bee2 = new Bee(2);
        Insect bee3 = new Bee(3);
        flower.registerInsect(bee1);
        flower.registerInsect(bee2);
        flower.registerInsect(bee3);
        flower.notifyInsect(true);
        flower.notifyInsect(false);
        flower.unreqisterInsect(bee1);
        flower.unreqisterInsect(bee2);
        flower.unreqisterInsect(bee3);
    }
}

//抽象观察者——昆虫类
interface Insect{
    void work();
    void unWork();
}
//具体观察
class Bee implements Insect{

    private int bId;

    public  Bee(int bId){
        this.bId = bId;
    }
    @Override
    public void work() {
        System.out.println("蜜蜂"+ bId +"采蜜");
    }

    @Override
    public void unWork() {
        System.out.println("蜜蜂"+ bId +"回巢");
    }
}

//抽象被观察者
interface  Plant{
   public void registerInsect(Insect insect);
   public void unreqisterInsect(Insect insect);
   public void notifyInsect(boolean isOpen);
}

//具体被观察者  定义一个集合存储观察者，实现相关方法
class Flower implements Plant{
    List<Insect> insects = new ArrayList<>();
    @Override
    public void registerInsect(Insect insect) {
        insects.add(insect);
    }

    @Override
    public void unreqisterInsect(Insect insect) {
        insects.remove(insect);
    }

    @Override
    public void notifyInsect(boolean isOpen) {
        if(isOpen){
            System.out.println("花开");
            for(Insect insect:insects)
                insect.work();
        }else {
            System.out.println("花闭");
            for(Insect insect:insects)
                insect.work();
        }

    }
}


/**
 * 拉方式代码
 */
interface User{
    public void update(OfficialAccount account);
}

class AndroidDev implements User{

    @Override
    public void update(OfficialAccount account) {
        System.out.println("-----");
    }
}
abstract class OfficialAccount{
   private List<User> userList = new ArrayList<>();

   public void reqisterUser(User user){
       userList.add(user);
   }
    public void unreqisterUser(User user){
        userList.remove(user);
    }

    public void notifyUse(){
        for(User user:userList){
            //拉的模式就那么简单，就是通知观察者更新的时候，把对应引用
            //也传过去，然后观察者要什么查什么而已
            user.update(this);
        }
    }

}
class CoderPig extends OfficialAccount{
   private String msg;
   public String getMsg(){
       return msg;
   }

   public void update(){
       this.msg= msg;
       System.out.println("============"+msg);
       this.notifyUse();
   }
}