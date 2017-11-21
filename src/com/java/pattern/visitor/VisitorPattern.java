package com.java.pattern.visitor;

import org.omg.CORBA.PUBLIC_MEMBER;

import java.util.ArrayList;
import java.util.List;

/**
 *  行为设计模式之一
 * Visitor :访问者设计模式
 *
 *  定义：封装一些作用域某种数据结构中的个元素的操作，在不改变这个数据结构的
 *  前提下，定义作用于这些元素的新的操作。
 *
 *  五个角色
 *    Visitor：抽象访问者，为每一个具体访问者角色创建一个访问操作接口。
 *    ConcreteVisitor：具体访问者，实现 Vistor 接口，并实现独自的操作。
 *    Element：元素角色，定义一个接收访问者方法，以访问者作为参数。
 *    ConcreteElement：具体元素角色，实现Element接口并实现接收操作。
 *    ObjectStructure：对象结构，使访问者能够访问到对应的元素。
 * Created by Administrator on 2017/11/14.
 */
public class VisitorPattern {
    public static void main(String[] args) {
        GameRoom room = new GameRoom();
        room.add(new Shooting());
        room.add(new Dancing());
        room.add(new Driving());

        Player player1 = new MalePlayer();
        Player player2 = new FemalePlayer();

        room.action(player1);
        room.action(player2);
    }
}

//元素角色
 interface Machine{
    public void accept(Player player);
}

//具体元素角色
class Shooting implements Machine{

    @Override
    public void accept(Player player) {
        player.visit(this);
    }

    public String feature(){
        return "投篮机";
    }
}
class Dancing implements Machine{

    @Override
    public void accept(Player player) {
        player.visit(this);
    }

    public String feature(){
        return "跳舞机";
    }
}

class Driving implements Machine{

    @Override
    public void accept(Player player) {
        player.visit(this);
    }
    public String feature(){
        return "开车";
    }
}

//抽象访问者
interface Player{
     public void visit(Shooting machine);
     public void visit(Dancing machine);
     public void visit(Driving machine);
}

//具体访问者
class MalePlayer implements Player{

    @Override
    public void visit(Shooting machine) {
        System.out.println("男性玩家："+machine.feature());
    }

    @Override
    public void visit(Dancing machine) {
        System.out.println("男性玩家："+machine.feature());
    }

    @Override
    public void visit(Driving machine) {
        System.out.println("男性玩家："+machine.feature());
    }
}

class FemalePlayer implements Player{

    @Override
    public void visit(Shooting machine) {
        System.out.println("女性玩家："+machine.feature());
    }

    @Override
    public void visit(Dancing machine) {
        System.out.println("女性玩家："+machine.feature());
    }

    @Override
    public void visit(Driving machine) {
        System.out.println("女性玩家："+machine.feature());
    }
}

//对象结构，用来管理元素集合
class GameRoom{
    private List<Machine> machineList = new ArrayList<>();

    public void add(Machine machine){
        machineList.add(machine);
    }

    public void action(Player player){
        for(Machine machine:machineList){
            machine.accept(player);
        }
    }
}
