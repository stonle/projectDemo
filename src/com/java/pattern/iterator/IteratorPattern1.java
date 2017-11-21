package com.java.pattern.iterator;

import java.util.ArrayList;
import java.util.Iterator;
/**
 * Iterator:迭代器模式
 * Created by Administrator on 2017/11/21.
 *
 * java.util.Iterable 接口只有一个方法：iterator()，也就说通过 iterator()这个方法去遍历聚
     集类中的所有方法或属性，基本上现在所有的高级的语言都有 Iterator 这个接口或者实现，Java 已经把迭代
     器给我们准备了，我们再去写迭代器，是不是“六指儿抓痒，多一道子”？所以呀，这个迭代器模式也有点没落
     了，基本上很少有项目再独立写迭代器了，直接使用 List 或者 Map 就可以完整的解决问题。
 */
public class IteratorPattern1 {
}

//定义一个接口，所有的项目都是一个接口
interface IProject {
    //增加项目
    public void add(String name,int num,int cost);
    //从老板这里看到的就是项目信息
    public String getProjectInfo();
    //获得一个可以被遍历的对象
    public IProjectIterator iterator();
}
//所有项目的信息类
@SuppressWarnings("all")
class Project implements IProject {
    //定义一个项目列表，说有的项目都放在这里
    private ArrayList<IProject> projectList = new ArrayList<IProject>();
    //项目名称
    private String name = "";
    //项目成员数量
    private int num = 0;
    //项目费用
    private int cost = 0;
    public Project(){
    }
    //定义一个构造函数，把所有老板需要看到的信息存储起来
    private Project(String name,int num,int cost){
//赋值到类的成员变量中
        this.name = name;
        this.num = num;
        this.cost=cost;
    }
    //增加项目
    public void add(String name,int num,int cost){
        this.projectList.add(new Project(name,num,cost));
    }
    //得到项目的信息
    public String getProjectInfo() {
        String info = "";
//获得项目的名称
        info = info+ "项目名称是：" + this.name;
//获得项目人数
        info = info + "\t项目人数: "+ this.num;
//项目费用
        info = info+ "\t 项目费用："+ this.cost;
        return info;
    }
    //产生一个遍历对象
    public IProjectIterator iterator(){
        return new ProjectIterator(this.projectList);
    }
}
//定义个Iterator接口
@SuppressWarnings("all")
interface IProjectIterator extends Iterator{
}
//定义一个迭代器
class ProjectIterator implements IProjectIterator {
    //所有的项目都放在这里ArrayList中
    private ArrayList<IProject> projectList = new ArrayList<IProject>();
    private int currentItem = 0;
    //构造函数传入projectList
    public ProjectIterator(ArrayList<IProject> projectList){
        this.projectList = projectList;
    }
    //判断是否还有元素，必须实现
    public boolean hasNext() {
//定义一个返回值
        boolean b = true;
        if(this.currentItem>=projectList.size() ||
                this.projectList.get(this.currentItem) == null){
            b =false;
        }
        return b;
    }
    //取得下一个值
    public IProject next() {
        return (IProject)this.projectList.get(this.currentItem++);
    }
    //删除一个对象
    public void remove() {
//暂时没有使用到
    }
}
