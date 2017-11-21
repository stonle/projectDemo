package com.java.pattern.composite;

import java.util.ArrayList;
import java.util.List;

/**
 * Composite : 组合模式
 * 描述：组合模式，又称为 部分整体模式，把具有相似的一组对象
 *       当做一个对象处理，用一种树状的结构来组合对象，再提供统一
 *       的方法去访问相似的对象，以此忽略掉对象与对象容器间的差别。
 *
 *    Component：抽象组件，为组合中的对象声明接口，让客户端
 *         可以通过这个接口来访问和管理整个对象结构，可以在里面为定义的
 *        功能提供缺省的实现，比如上面的AbstractMenu类。
 *    Composite：容器组件，继承抽象组件，实现抽象组件中与
 *        叶子组件相关的操作，比如上面的Menu类重写了get，set方法。
 *    Leaf：叶子组件，定义和实现叶子对象的行为，不再包含其它
 *        的子节点对象，比如上面的MilkTea，Juice，HandCake，FishBall。
 * Created by Administrator on 2017/11/8.
 */
public class CompositePattern {
}

/**
 * 抽象组件：抽象组件，为组合中的对象声明接口，让客户端
 * 可以通过这个接口来访问和管理整个对象结构，可以在里面为定义的
 * 功能提供缺省的实现，比如上面的AbstractMenu类。
 */
abstract class AbstractMenu{

    abstract void add(AbstractMenu menu);

    abstract AbstractMenu get(int index);

    abstract String  getString();
}

/**
 * Leaf：叶子组件，定义和实现叶子对象的行为，不再包含其它
 *   的子节点对象，比如上面的MilkTea，Juice，HandCake，FishBall。
 */
class Milk extends  AbstractMenu{

    private String name;
    private String desc;
    private int price;
    Milk(String name,String desc,int price){
        this.desc = desc;
        this.desc = name;
        this.price = price;

    }
    @Override
    void add(AbstractMenu menu) {}

    @Override
    AbstractMenu get(int index) {
        return null;
    }

    @Override
    String getString() {
        return null;
    }
}

/**
 * Composite：容器组件，继承抽象组件，实现抽象组件中与
 * 叶子组件相关的操作，比如上面的Menu类重写了get，set方法。
 *
 */
class  Menu extends  AbstractMenu{

    private String nmae;
    private String desc;
    private List<AbstractMenu> menus = new ArrayList<>();

    Menu(String desc,String nmae){
        this.desc = desc;
        this.nmae = nmae;
    }
    @Override
    void add(AbstractMenu menu) {
         menus.add(menu);
    }

    @Override
    AbstractMenu get(int index) {
        return menus.get(index);
    }

    @Override
    String getString() {
        StringBuilder sb = new StringBuilder("\n[菜单]"+nmae+"信息"+desc +"\n");
        for(AbstractMenu mme:menus){
            sb.append(mme.getString()).append("\n");
        }
        return sb.toString();
    }
}