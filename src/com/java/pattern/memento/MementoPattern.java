package com.java.pattern.memento;

/**
 * Memento : 备忘录模式
 *        在不破坏封装性的前提下，捕获一个对象的内部状态，并在该对象之外的保存
 * 这个状态。
 *
 * 三个角色：
 *      Originator：发起人，创建一个备忘录，可以记录，恢复自身的内部状态，还可根据
 *                  需求决定存储那些内部状态。
 *      Memento：备忘录，存储发起人角色的内部状态，并防止外部对象访问备忘录。
 *      Caretaker：管理者，存储备忘录，不能对备忘录内容进行访问，只能将其传递
 *                 给其他对象。
 * Created by Administrator on 2017/11/13.
 */
public class MementoPattern {

    public static void main(String[] args) {
        Caretaker caretaker = new Caretaker();
        Character character = new Character(2000,1000,5000);
        //存档
        System.out.println("====== 存档中...=====");
        character.showMsg();
        caretaker.setMemento(character.save());

        System.out.println("====== 单挑Boss,不敌,金钱扣除一半.... ====");
        character.setHp(0);
        character.setHp(0);
        character.setMoney(250);
        character.showMsg();
        //读档
        System.out.println("===== 读取存档中....====");
        character.restore(caretaker.getMemento());
        character.showMsg();
    }
}

//备忘录类
class Memento{
    private int hp;
    private int mp;
    private int money;

    public Memento(int hp,int mp,int money){
        this.hp = hp;
        this.mp = mp;
        this.money = money;
    }

    public int getHp() {
        return hp;
    }

    public int getMp() {
        return mp;
    }

    public int getMoney() {
        return money;
    }
}

/**
 * 角色类（发起人角色），除了属性定义，还有两件关键的事
 * 要做：定义保存方法，保存自身状态；定义恢复方法，传入备忘录对象，
 * 自行回复需要回复的项
 */

class Character{
    private int hp;
    private int mp;
    private int money;
    public Character(int hp,int mp,int money){
        this.hp = hp;
        this.money = money;
        this.mp = mp;

    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getMp() {
        return mp;
    }

    public void setMp(int mp) {
        this.mp = mp;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }


    public void showMsg(){
        System.out.println("当前状态:|HP:"+ hp + "| MP:" + mp +"| 金钱:"+money);
    }

    //创建一个备忘录，保存当前自身状态
    public  Memento save(){
        return new Memento(hp,mp,money);
    }
    //传入一个备忘录对象，恢复内部状态
    public void  restore(Memento memento){
        this.hp = memento.getHp();
        this.money = memento.getMoney();
        this.mp = memento.getMp();

    }
}

//备忘录管理者类，只负责备忘录对象的传递
class Caretaker{
    private Memento memento;
    public Memento getMemento() {
        return memento;
    }
    public void setMemento(Memento memento) {
        this.memento = memento;
    }
}

