package com.java.pattern.template;

/**
 * templateMethod : 模板方法模式
 * Created by Administrator on 2017/11/20.
 */
public class TemplateMthondPattern1 {
}
//
abstract class HummerModel {
    /*
    * 首先，这个模型要能够被发动起来，别管是手摇发动，还是电力发动，反正
    * 是要能够发动起来，那这个实现要在实现类里了
    */
    protected abstract void start();
    //能发动，那还要能停下来，那才是真本事
    protected abstract void stop();
    //喇叭会出声音，是滴滴叫，还是哔哔叫
    protected abstract void alarm();
    //引擎会轰隆隆的响，不响那是假的
    protected abstract void engineBoom();
    //那模型应该会跑吧，别管是人推的，还是电力驱动，总之要会跑
    final public void run(){
        //先发动汽车
        this.start();
        //引擎开始轰鸣
        this.engineBoom();
        //然后就开始跑了，跑的过程中遇到一条狗挡路，就按喇叭
        //喇嘛想让它响就响，不想让它响就不响
        if(this.isAlarm()){
            this.alarm();
        }
        //到达目的地就停车
        this.stop();
    }
    //钩子方法，默认喇叭是会响的
    protected boolean isAlarm(){
        return true;
    }
}
class HummerH1Model extends HummerModel {
    private boolean alarmFlag = true; //是否要响喇叭
    @Override
    protected void start() {
        System.out.println("悍马H1发动...");
    }

    @Override
    protected void stop() {
        System.out.println("悍马H1停车...");
    }

    @Override
    protected void alarm() {
        System.out.println("悍马H1鸣笛...");
    }

    @Override
    protected void engineBoom() {
        System.out.println("悍马H1引擎声音是这样在...");
    }
    @Override
    protected boolean isAlarm() {
        return this.alarmFlag;
    }
    //要不要响喇叭，是有客户的来决定的
    public void setAlarm(boolean isAlarm){
        this.alarmFlag = isAlarm;
    }
}
class HummerH2Model extends HummerModel {

    @Override
    protected void start() {
        System.out.println("悍马H2发动...");
    }

    @Override
    protected void stop() {
        System.out.println("悍马H1停车...");
    }

    @Override
    protected void alarm() {
        System.out.println("悍马H2鸣笛...");
    }

    @Override
    protected void engineBoom() {
        System.out.println("悍马H2引擎声音是这样在...");
    }
}

