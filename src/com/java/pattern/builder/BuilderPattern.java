package com.java.pattern.builder;

/**
 * Created by Administrator on 2017/11/4.
 * 设计模式二：建造者模式
 *  模式定义：
 *      建造者模式(Builder Pattern)：将一个复杂对象的构建与它的表示分离，使得同样的构建过程可以创建不同的表示。
 *      建造者模式是一步一步创建一个复杂的对象，它允许用户只通过指定复杂对象的类型和内容就可以构建它们，
 *      用户不需要知道内部的具体构建细节。建造者模式属于对象创建型模式。根据中文翻译的不同，建造者模式又可以称为生成器模式。
 *
 *      模式结构
 *        建造者模式包含如下角色：
 *        1、Builder（抽象建造者）：它为创建一个产品Product对象的各个部件指定抽象接口，并有方法返回产品对象。
 *        2、ConcreteBuilder（具体建造者）：它实现了Builder接口或抽象方法，实现各个部件的具体构造和装配方法，
 *        定义并明确它所创建的复杂对象。
 *        3、Product（产品角色）：它是被构建的复杂对象，通常包含多个组成部件。
 *        4、Director（指挥者）：它负责安排复杂对象的建造次序，指挥者与抽象建造者之间存在关联关系，
 *        可以在其construct()建造方法中调用建造者对象的部件构造与装配方法，完成复杂对象的建造。
 */


public class BuilderPattern {
    public static void main(String[] args) {
        ConcreteBuilder cBuilder = new ConcreteBuilder();
        Director director = new Director(cBuilder);
        director.construct();
    }
}

/**
 *  抽象建造者
 */
abstract  class  Builder{
    protected Product product = new Product();

    protected abstract void buildPartA();

    protected abstract void buildPartB();

    protected abstract void buildPartC();

    public Product getProduct(){
        return product;
    }
}

/**
 *被建造的对象：产品
 */
class Product {
    // 产品部件A
    private String partA;
    // 产品部件B
    private String partB;
    // 产品部件C
    private String partC;

    public String getPartA() {
        return partA;
    }

    public void setPartA(String partA) {
        this.partA = partA;
    }

    public String getPartB() {
        return partB;
    }

    public void setPartB(String partB) {
        this.partB = partB;
    }

    public String getPartC() {
        return partC;
    }

    public void setPartC(String partC) {
        this.partC = partC;
    }
}

/**
 * 具体的建造者对象<br>
 * 建造复杂产品的各个组成部件、最后由指挥者类<Director>进行组装成完整的产品对象
 */
class ConcreteBuilder extends Builder{

    @Override
    protected void buildPartA() {
        product.setPartA("build Part A");
        System.out.println("正在建造产品部件A");
    }

    @Override
    protected void buildPartB() {
        product.setPartB("build Part B");
        System.out.println("正在建造产品部件B");
    }

    @Override
    protected void buildPartC() {
        product.setPartC("build Part C");
        System.out.println("正在建造产品部件C");
    }
}

/**
 * 负责 安排/组装 复杂对象的建造次序
 */
class Director{
    private Builder builder;
    public Director(Builder builder){
        this.builder = builder;
    }

    /**
     * 产品对象的构建与组装
     * @return
     */
    public Product construct(){
        System.out.println("--- 指挥者开始 构建产品 ---");
        builder.buildPartA();
        builder.buildPartB();
        builder.buildPartC();
        System.out.println("--- 指挥者 构建产品 完成 ---");
        return builder.getProduct();
    }
}





