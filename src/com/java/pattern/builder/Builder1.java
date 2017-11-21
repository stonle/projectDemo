package com.java.pattern.builder;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/11/20.
 */
public class Builder1 {
}
//定义一个车辆模型的抽象类，所有的车辆模型都继承这里类
abstract class CarModel{
    //这个参数是各个基本方法执行的顺序
    private ArrayList<String> sequence = new ArrayList<String>();
    /*
     * 模型是启动开始跑了
     */
    protected abstract void start();
    //能发动，那还要能停下来，那才是真本事
    protected abstract void stop();
    //喇叭会出声音，是滴滴叫，还是哔哔叫
    protected abstract void alarm();
    //引擎会轰隆隆的响，不响那是假的
    protected abstract void engineBoom();
    //那模型应该会跑吧，别管是人推的，还是电力驱动，总之要会跑
    //那模型应该会跑吧，别管是人推的，还是电力驱动，总之要会跑
    final public void run() {
//循环一遍，谁在前，就先执行谁
        for(int i=0;i<this.sequence.size();i++){
            String actionName = this.sequence.get(i);
            if(actionName.equalsIgnoreCase("start")){ //如果是start关键字，
                this.start(); //开启汽车
            }else if(actionName.equalsIgnoreCase("stop")){ //如果是stop关键字
                this.stop(); //停止汽车
            }else if(actionName.equalsIgnoreCase("alarm")){ //如果是alarm关键字
                this.alarm(); //喇叭开始叫了
            }else if(actionName.equalsIgnoreCase("engine boom")){ //如果是engineboom关键字
                this.engineBoom(); //引擎开始轰鸣
            }
        }
    }
    //把传递过来的值传递到类内
    final public void setSequence(ArrayList<String> sequence){
        this.sequence = sequence;
    }
}

class BenzModel extends CarModel{

    @Override
    protected void start() {
        System.out.println("奔驰车跑起来是这个样子的...");
    }

    @Override
    protected void stop() {
        System.out.println("奔驰车应该这样停车...");
    }

    @Override
    protected void alarm() {
        System.out.println("奔驰车的喇叭声音是这个样子的...");
    }

    @Override
    protected void engineBoom() {
        System.out.println("奔驰车的引擎是这个声音的...");
    }
}
class BMWModel extends CarModel{

    @Override
    protected void start() {
        System.out.println("宝马车跑起来是这个样子的...");
    }

    @Override
    protected void stop() {
        System.out.println("宝马车应该这样停车...");
    }

    @Override
    protected void alarm() {
        System.out.println("宝马车的喇叭声音是这个样子的...");
    }

    @Override
    protected void engineBoom() {
        System.out.println("宝马车的引擎是这个声音的...");
    }
}
abstract class CarBuilder {
    //建造一个模型，你要给我一个顺序要，就是组装顺序
    public abstract void setSequence(ArrayList<String> sequence);
    //设置完毕顺序后，就可以直接拿到这个车辆模型
    public abstract CarModel getCarModel();
}
class BenzBuilder extends CarBuilder {
    private BenzModel benz = new BenzModel();
    @Override
    public CarModel getCarModel() {
        return this.benz;
    }
    @Override
    public void setSequence(ArrayList<String> sequence) {
        this.benz.setSequence(sequence);
    }
}
class BMWBuilder extends CarBuilder {
    private BMWModel bmw = new BMWModel();
    @Override
    public CarModel getCarModel() {
        return this.bmw;
    }
    @Override
    public void setSequence(ArrayList<String> sequence) {
        this.bmw.setSequence(sequence);
    }
}

class Directors {
    private ArrayList<String> sequence = new ArrayList();
    private BenzBuilder benzBuilder = new BenzBuilder();
    private BMWBuilder bmwBuilder = new BMWBuilder();
    /*
    * A类型的奔驰车模型，先start,然后stop,其他什么引擎了，喇叭一概没有
    */
    public BenzModel getABenzModel() {
        //清理场景，这里是一些初级程序员不注意的地方
        this.sequence.clear();
        //这只ABenzModel的执行顺序
        this.sequence.add("start");
        this.sequence.add("stop");
           //按照顺序返回一个奔驰车
        this.benzBuilder.setSequence(this.sequence);
        return (BenzModel) this.benzBuilder.getCarModel();
    }
    /*
    * B型号的奔驰车模型，是先发动引擎，然后启动，然后停止，没有喇叭
    */
    public BenzModel getBBenzModel(){
        this.sequence.clear();
        this.sequence.add("engine boom");
        this.sequence.add("start");
        this.sequence.add("stop");
        this.benzBuilder.setSequence(this.sequence);
        return (BenzModel)this.benzBuilder.getCarModel();
    }
    /*
* C型号的宝马车是先按下喇叭（炫耀嘛），然后启动，然后停止
*/
    public BMWModel getCBMWModel(){
        this.sequence.clear();
        this.sequence.add("alarm");
        this.sequence.add("start");
        this.sequence.add("stop");
        this.bmwBuilder.setSequence(this.sequence);
        return (BMWModel)this.bmwBuilder.getCarModel();
    }
    /*
     * D类型的宝马车只有一个功能，就是跑，启动起来就跑，永远不停止，牛叉
     */
    public BMWModel getDBMWModel(){
        this.sequence.clear();
        this.sequence.add("start");
        this.bmwBuilder.setSequence(this.sequence);
       return (BMWModel)this.benzBuilder.getCarModel();
    }
    /*
     * 这里还可以有很多方法，你可以先停止，然后再启动，或者一直停着不动，静态的嘛
     * 导演类嘛，按照什么顺序是导演说了算
     */
}
