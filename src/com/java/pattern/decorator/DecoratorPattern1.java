package com.java.pattern.decorator;

/**
 * Created by Administrator on 2017/11/8.
 */
public class DecoratorPattern1 {
    public static void main(String[] args) {
        //装饰着模式测试， IO也是用的装饰者模式
        Tea  tea1 = new JinJu(new HongDou(new YeGuo(new ZhenZhu(new MilkTea()))));
        System.out.println(tea1.getName());
        System.out.println(tea1.price());
    }
}

/**
 * 抽象主键
 */
abstract class Tea{
    private String name = "茶";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public abstract int price();
}

/**
 * 抽象装饰类
 */
abstract class Decoratosr extends Tea{
    public abstract  String getName();
}

/**
 * 具体组件
 */
class MilkTea extends Tea{

    MilkTea(){
        //默认调用
        super.setName("奶茶");
    }
    @Override
    public int price() {
        return 5;
    }
}

class LemonTea extends Tea{

    public  LemonTea(){
        setName("柠檬茶");
    }
    @Override
    public int price() {
        return 3;
    }
}

class ZhenZhu extends  Decoratosr{
    //这里用到了聚合关系
    private Tea tea;
    ZhenZhu(Tea tea){
        this.tea = tea;
    }
    @Override
    public int price() {
        return 2 + tea.price();
    }

    @Override
    public String getName() {
        return "珍珠"+tea.getName();
    }
}

class YeGuo extends  Decoratosr{
    private Tea tea;
    YeGuo(Tea tea){
        this.tea = tea;
    }
    @Override
    public int price() {
        return 2 + tea.price();
    }

    @Override
    public String getName() {
        return "椰果" + tea.getName();
    }
}
class HongDou extends  Decoratosr{
    private Tea tea;
    HongDou(Tea tea){
     this.tea = tea;
    }
    @Override
    public int price() {
        return 2 + tea.price();
    }

    @Override
    public String getName() {
        return "红豆" + tea.getName();
    }
}

class JinJu extends Decoratosr{
    Tea tea;
    JinJu(Tea tea){
        this.tea = tea;
    }

    @Override
    public int price() {
        return 1 + tea.price();
    }

    @Override
    public String getName() {
        return "金桔" + tea.getName();
    }
}

