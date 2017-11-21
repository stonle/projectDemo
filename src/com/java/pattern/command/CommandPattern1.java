package com.java.pattern.command;

/**
 * command:命令模式
 *  行为型模式主要用于描述类或对象怎样交互以及怎样分配职责，
 * Created by Administrator on 2017/11/21.
 *
 * Receiver 角色：这个就是干活的角色，命令传递到这里是应该被执行的，具体到上面我们的例子中就是
  Group 的三个实现类；

 Command 角色：就是命令，需要我执行的所有命令都这里声明；

 Invoker 角色：调用者，接收到命令，并执行命令，例子中我这里项目经理就是这个角色；
 */
public class CommandPattern1 {
}
//项目组分成了三个组，每个组还是要接受增删改的命令
abstract class Group {
    //甲乙双方分开办公，你要和那个组讨论，你首先要找到这个组
    public abstract void find();

    //被要求增加功能
    public abstract void add();

    //被要求删除功能
    public abstract void delete();

    //被要求修改功能
    public abstract void change();

    //被要求给出所有的变更计划
    public abstract void plan();
}
//需求组的职责是和客户谈定需求，这个组的人应该都是业务领域专家
class RequirementGroup extends Group {
    //客户要求需求组过去和他们谈
    public void find() {
        System.out.println("找到需求组...");
    }
    //客户要求增加一项需求
    public void add() {
        System.out.println("客户要求增加一项需求...");
    }
    //客户要求修改一项需求
    public void change() {
        System.out.println("客户要求修改一项需求...");
    }
    //客户要求删除一项需求
    public void delete() {
        System.out.println("客户要求删除一项需求...");
    }
    //客户要求出变更计划
    public void plan() {
        System.out.println("客户要求需求变更计划...");
    }
}
//美工组的职责是设计出一套漂亮、简单、便捷的界面
class PageGroup extends Group {
    //首先这个美工组应该被找到吧，要不你跟谁谈？
    public void find() {
        System.out.println("找到美工组...");
    }
    //美工被要求增加一个页面
    public void add() {
        System.out.println("客户要求增加一个页面...");
    }
    //客户要求对现有界面做修改
    public void change() {
        System.out.println("客户要求修改一个页面...");
    }
    //甲方是老大，要求删除一些页面
    public void delete() {
        System.out.println("客户要求删除一个页面...");
    }
    //所有的增删改那要给出计划呀
    public void plan() {
        System.out.println("客户要求页面变更计划...");
    }
}
//代码组的职责是实现业务逻辑，当然包括数据库设计了
class CodeGroup extends Group {
    //客户要求代码组过去和他们谈
    public void find() {
        System.out.println("找到代码组...");
    }
    //客户要求增加一项功能
    public void add() {
        System.out.println("客户要求增加一项功能...");
    }
    //客户要求修改一项功能
    public void change() {
        System.out.println("客户要求修改一项功能...");
    }
    //客户要求删除一项功能
    public void delete() {
        System.out.println("客户要求删除一项功能...");
    }
    //客户要求出变更计划
    public void plan() {
        System.out.println("客户要求代码变更计划...");
    }
}

//命令的抽象类，我们把客户发出的命令定义成一个一个的对象
abstract class Commands{
    //把三个组都定义好，子类可以直接使用
    protected RequirementGroup rg = new RequirementGroup(); //需求组
    protected PageGroup pg = new PageGroup(); //美工组
    protected CodeGroup cg = new CodeGroup(); //代码组;
    //只要一个方法，你要我做什么事情
    public abstract void execute();
}
//这个简单，看两个具体的实现类，先看 AddRequeirementCommand 类, 这个类的作用就是增加一项需求。
//增加一项需求
class AddRequirementCommand extends Commands {
    //执行增加一项需求的命令
    public void execute() {
        // 找到需求组
        super.rg.find();
        // 增加一份需求
        super.rg.add();
         // 页面也要增加
        super.pg.add();
        // 功能也要增加
        super.cg.add();
        // 给出计划
        super.rg.plan();
    }
}
//看删除一个页面的命令,DeletePageCommand 类：
class DeletePageCommand extends Commands {
    //执行删除一个页面的命令
    public void execute() {
//找到页面组
        super.pg.find();
//删除一个页面
        super.rg.delete();
//给出计划
        super.rg.plan();
    }
}
//接头人的职责就是接收命令，并执行
class Invokers {
    //什么命令
    private Commands command;

    //客户发出命令
    public void setCommand(Commands command) {
        this.command = command;
    }

    //执行客户的命令
    public void action() {
        this.command.execute();
    }
}