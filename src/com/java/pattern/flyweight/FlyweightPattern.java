package com.java.pattern.flyweight;

import java.util.HashMap;
import java.util.Map;

/**
 * Flyweight : 享元模式
 *   当存在多个相同对象的时候，使用享元模式可以减少相同对象
 * 创建引起的内存消耗，提高程序性能。
 * 概念定义：内部状态与外部状态
 *  内部状态：固定不变可共享的的部分，存储在享元对象内部，比如这里的花色。
 *  外部状态：可变不可共享的部分，一般由客户端传入享元对象内部，比如这里的大小。
 * 三个角色
 *    Flyweight：享元对象的抽象父类或者接口，通过这个接口，享元对象可以接受并作用于外部状态；
 *    ConcreteFlyweight：具体享元实现对象，继承或实现Flyweight并为内部状态增加存储空间。
 *    FlyweightFactory：享元工厂，创建并管理共享的享元对象，并对外提供访问共享享元对象的接口。
 *
 *使用场景：1.系统有大量相同或者相似的对象，消耗大量内存
 *          2.需要缓冲池
 *优缺点：优点：大大减少对象的创建，降低系统的内存，使效率提高
 *        缺点：使得系统变得复杂，需要分离出内部状态和外部状态，这使得程序的逻辑复杂化。
 *
 * Created by Administrator on 2017/11/9.
 */
public class FlyweightPattern {
    public static void main(String[] args) {
        for (int k = 0;k<10;k++){
            Card card = null;
            //随机花色
            switch ((int)Math.random()*4){
                case 0:card = PokerFactory.getPoker(PokerFactory.Spade);break;
                case 1:card = PokerFactory.getPoker(PokerFactory.Herat);break;
                case 2:card = PokerFactory.getPoker(PokerFactory.Club);break;
                case 3:card = PokerFactory.getPoker(PokerFactory.Diamond);break;
            }
            if (card != null){
                int num = (int)(Math.random()*13 + 1);
                switch (num){
                    case 11:card.showCard("J");break;
                    case 12:card.showCard("Q");break;
                    case 13:card.showCard("K");break;
                    default:card.showCard(num+"");break;
                }
            }
        }
    }
}

abstract class Card{
    abstract void showCard(String num);
}

class SpadeCard extends Card{

    @Override
    void showCard(String num) {
        System.out.println("黑桃"+num);
    }
}

class HeartCard extends  Card{

    @Override
    void showCard(String num) {
        System.out.println("红桃"+num);
    }
}

class ClubCard extends Card{

    @Override
    void showCard(String num) {
        System.out.println("梅花"+num);
    }
}

class DiamondCard extends Card{

    @Override
    void showCard(String num) {
        System.out.println("方块"+num);
    }
}
//享元工厂
class PokerFactory{
    static final int Spade = 0;
    static final int Herat = 1;
    static final int Club = 2;
    static final int Diamond = 3;
    public static Map<Integer,Card> pokers = new HashMap<>();
    public static Card getPoker(int color){
        if(pokers.containsKey(color)){
            System.out.println("对象已存在，对象复用.......");
            return pokers.get(color);
        }else{
            System.out.println("对象不存在，新建对象.........");
            Card card;
            switch (color){
                case Spade:card = new SpadeCard();break;
                case Herat:card = new HeartCard();break;
                case Club:card = new ClubCard();break;
                case Diamond: card = new DiamondCard(); break;
                default: card = new SpadeCard();break;
            }
            pokers.put(color,card);
            return card;
        }
    }
}


