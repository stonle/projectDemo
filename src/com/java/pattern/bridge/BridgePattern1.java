package com.java.pattern.bridge;

/**
 * bridge:桥接模式
 *
 * 桥梁模式的业务抽象角色
 *   （Abstraction）和业务实现角色（Implementor）
 *      桥梁模式描述了类间弱关联关系
 *      继承不能说它不好，非常好，但是有缺点的，我们可以扬长避短，对于比较明确不发生变化的，则通过继承
 * 来完成，若不能确定是否会发生变化的，那就认为是会发生变化，则通过桥梁模式来解决，这才是一个完美的世
 * 界
 * Created by Administrator on 2017/11/21.
 */
public class BridgePattern1 {
    public static void main(String[] args) {
        System.out.println("-------房地产公司是这个样子运行的-------");
        //先找到我的公司
        HouseCorp houseCorp =new HouseCorp();
        //看我怎么挣钱
        houseCorp.makeMoney();
        System.out.println("\n");
        System.out.println("-------服装公司是这样运行的-------");
        ClothesCorp clothesCorp = new ClothesCorp();
        clothesCorp.makeMoney();
    }
}
abstract class Corp {
    /*
     * 是公司就应该有生产把，甭管是什么软件公司还是制造业公司
     * 那每个公司的生产的东西都不一样，所以由实现类来完成
     */
    protected abstract void produce();
    /*
    * 有产品了，那肯定要销售呀，不销售你公司怎么生存
    */
    protected abstract void sell();
    //公司是干什么的？赚钱的呀，不赚钱傻子才干
    public void makeMoney(){
         //每个公司都是一样，先生产
        this.produce();
         //然后销售
        this.sell();
    }
}
class HouseCorp extends Corp {

    @Override
    protected void produce() {
        System.out.println("房地产公司盖房子...");
    }

    @Override
    protected void sell() {
        System.out.println("房地产公司出售房子...");
    }

    //房地产公司很High了，赚钱，计算利润
    public void makeMoney(){
        super.makeMoney();
        System.out.println("房地产公司赚大钱了...");
    }
}
class ClothesCorp extends Corp {

    @Override
    protected void produce() {
        System.out.println("服装公司生产衣服...");
    }

    @Override
    protected void sell() {
        System.out.println("服装公司出售衣服...");
    }
    //服装公司不景气，但怎么说也是赚钱行业也
    public void makeMoney(){
        super.makeMoney();
        System.out.println("服装公司赚小钱...");
    }

}


//这是我整个集团公司的产品类

abstract class Product {
    //甭管是什么产品它总要是能被生产出来
    public abstract void beProducted();
    //生产出来的东西，一定要销售出去，否则扩本呀
    public abstract void beSelled();
}
// 这是我集团公司盖的房子
class House extends Product{
    //豆腐渣就豆腐渣呗，好歹也是个房子
    public void beProducted() {
        System.out.println("生产出的房子是这个样子的...");
    }
    //虽然是豆腐渣，也是能够销售出去的
    public void beSelled() {
        System.out.println("生产出的房子卖出去了...");
    }
}
class Clothes extends Product {
    public void beProducted() {
        System.out.println("生产出的衣服是这个样子的...");
    }
    public void beSelled() {
        System.out.println("生产出的衣服卖出去了...");
    }
}
class IPod extends Product {
    public void beProducted() {
        System.out.println("生产出的iPod是这个样子的...");
    }
    public void beSelled() {
        System.out.println("生产出的iPod卖出去了...");
    }
}

//定义一个公司的抽象类
//这里多了个有参构造，其目的是要继承的子类都必选重写自己的有参构造函数，把产品类传递进来;
abstract class Corp1 {
    //定义一个产品对象，抽象的了，不知道具体是什么产品
    private Product product;
    //构造函数，由子类定义传递具体的产品进来
    public Corp1(Product product){
        this.product = product;
    }
    //公司是干什么的？赚钱的呀，不赚钱傻子才干
    public void makeMoney(){
          //每个公司都是一样，先生产
        this.product.beProducted();
         //然后销售
        this.product.beSelled();
    }
}
class HouseCorp1 extends Corp1 {
    public HouseCorp1(House house) {
        super(house);
    }
    //房地产公司很High了，赚钱，计算利润
    public void makeMoney(){
        super.makeMoney();
        System.out.println("房地产公司赚大钱了...");
    }
}
//继续看山寨公司的实现:我是山寨老大，你流行啥我就生产啥
class ShanZhaiCorp1 extends Corp1 {
    //产什么产品，不知道，等被调用的才知道
    public ShanZhaiCorp1(Product product) {
        super(product);
    }
    //狂赚钱
    public void makeMoney(){
        super.makeMoney();
        System.out.println("我赚钱呀...");
    }
}