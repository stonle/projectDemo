package com.java.pattern.adapter;

/**
 * 设计模式之五： 适配器模式
 * Created by Administrator on 2017/11/6.
 *
 * 定义：适配器模式把一个类的接口变换成客户端所期待的另一种接口，
 *       从而使原本因接口不匹配而无法在一起工作的两个类能够在一起工作。
 *
 * 简单点说：可以加个中间类，用它来协调两类之间的关系，完成相关业务。这种玩法就叫适配器模式！
 *
 * 两种适配器模式：
 *       根据适配器类与适配者类的关系不同，适配器模式可分为 类适配器 和
 *       对象适配器两种，尽管都是三个角色，但还是有些差别的！
 *       另外，类适配器的适配器和适配者是 继承 关系，而对象适配器则是 引用 关系。
 *
 * 类适配器的三个角色：
 *   目标接口(Target)：客户所期待的接口，目标是接口；
 *   需要适配的类(Adaptee )：需要适配的类或适配者类；
 *   适配器(Adapter)：实现了抽象目标类接口Target，并继承了适配者类Adaptee
 *  对象适配器的三个角色：
 *    目标接口(Target)：客户所期待的接口，可以是具体的或抽象的类，也可以是接
 *    需要适配的类(Adaptee)：需要适配的类或适配者类；
 *    适配器(Adapter)：通过包装一个需要适配的对象，把原接口转换成目标接口；
 *
 * 对象适配器支持传入一个被适配器对象，因此可以做到对多种被适
 * 配接口进行适配。而类适配器直接继承，无法动态修改，所以一般情况
 * 下对象适配器使用得更多！（Java不支持多重继承！！！）
 */


public class AdapterPattern {
}

interface Chiness{
    void speakChiness(String str);
}

class  English{
    void speakEnglish(String str){

    }
}

//适配器
class Translator implements Chiness{
    private English english = new English();
    public Translator(English english){
        this.english = english;
    }
    @Override
    public void speakChiness(String str) {
        english.speakEnglish(str);
    }
}