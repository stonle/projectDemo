package com.java.pattern.abstractFactory;


/**
 *  抽象工厂模式
 * Created by Administrator on 2017/11/20.
 */
public class AbstractFactory2
{

}
//  人类接口
interface Human{
   //首先定义什么是人类
    //人是愉快的，会笑的，本来是想用smile表示，想了一下laugh更合适，好长时间没有大笑了；
    public void laugh();
    //人类还会哭，代表痛苦
    public void cry();
    //人类会说话
    public void talk();
    //定义性别
    public void sex();
}

abstract class AbstractYellowHuman implements Human{
    public void cry() {
        System.out.println("黄色人种会哭");
    }
    public void laugh() {
        System.out.println("黄色人种会大笑，幸福呀！");
    }
    public void talk() {
        System.out.println("黄色人种会说话，一般说的都是双字节");
    }
}
abstract class AbstractWhiteHuman implements Human{
    public void cry() {
        System.out.println("白色人种会哭");
    }
    public void laugh() {
        System.out.println("白色人种会大笑，侵略的笑声");
    }
    public void talk() {
        System.out.println("白色人种会说话，一般都是但是单字节！");
    }
}
abstract class AbstractBlackHuman implements Human {
    public void cry() {
        System.out.println("黑人会哭");
    }
    public void laugh() {
        System.out.println("黑人会笑");
    }
    public void talk() {
        System.out.println("黑人可以说话，一般人听不懂");
    }
}
class YellowFemaleHuman extends AbstractYellowHuman {
    public void sex() {
        System.out.println("该黄种人的性别为女...");
    }
}
class YellowMaleHuman extends AbstractYellowHuman {
    public void sex() {
        System.out.println("该黄种人的性别为男....");
    }
}

class WhiteFemaleHuman extends AbstractWhiteHuman {
    @Override
    public void sex() {
        System.out.println("该白种人的性别为女....");
    }
}
class WhiteMaleHuman extends AbstractWhiteHuman {
    public void sex() {
        System.out.println("该白种人的性别为男....");
    }
}
class BlackFemaleHuman extends AbstractBlackHuman {
    public void sex() {
        System.out.println("该黑种人的性别为女...");
    }
}
class BlackMaleHuman extends AbstractBlackHuman {
    public void sex() {
        System.out.println("该黑种人的性别为男...");
    }
}

/**
 * 世界上有哪些类型的人，列出来
 * JDK 1.5开始引入enum类型也是目的的，吸引C程序员转过来
 */
enum HumanEnum {
    //把世界上所有人类型都定义出来
    YelloMaleHuman("com.java.pattern.abstractFactory.YellowMaleHuman"),
    YelloFemaleHuman("com.java.pattern.abstractFactory.YellowFemaleHuman"),
    WhiteFemaleHuman("com.java.pattern.abstractFactory.WhiteFemaleHuman"),
    WhiteMaleHuman("com.java.pattern.abstractFactory.WhiteMaleHuman"),
    BlackFemaleHuman("com.java.pattern.abstractFactory.BlackFemaleHuman"),
    BlackMaleHuman("com.java.pattern.abstractFactory.BlackMaleHuman");
    private String value = "";
    //定义构造函数，目的是Data(value)类型的相匹配
    private HumanEnum(String value){
        this.value = value;
    }
    public String getValue(){
        return this.value;
    }
    /*
        * java enum类型尽量简单使用，尽量不要使用多态、继承等方法
        * 毕竟用Clas完全可以代替enum
        */
}

/**
 * 工厂接口
 */
interface HumanFactory{
    //制造黄色人种
    public Human createYellowHuman();
    //制造一个白色人种
    public Human createWhiteHuman();
    //制造一个黑色人种
    public Human createBlackHuman();
}
abstract class AbstractHumanFactory implements HumanFactory {
    /*
* 给定一个性别人种，创建一个人类出来 专业术语是产生产品等级
*/
    protected Human createHuman(HumanEnum humanEnum) {
        Human human = null;
        if(!humanEnum.getValue().equals("")){
            try {
                human= (Human) Class.forName(humanEnum.getValue()).newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
       return human;
    }

}
//具体工厂类
class MaleHumanFactory extends AbstractHumanFactory{

    //创建一个男性黑种人
    public Human createBlackHuman() {
        return super.createHuman(HumanEnum.BlackMaleHuman);
    }
    //创建一个男性白种人
    public Human createWhiteHuman() {
        return super.createHuman(HumanEnum.WhiteMaleHuman);
    }
    //创建一个男性黄种人
    public Human createYellowHuman() {
        return super.createHuman(HumanEnum.YelloMaleHuman);
    }
}

class FemaleHumanFactory extends AbstractHumanFactory {
    //创建一个女性黑种人
    public Human createBlackHuman() {
        return super.createHuman(HumanEnum.BlackFemaleHuman);
    }
    //创建一个女性白种人
    public Human createWhiteHuman() {
        return super.createHuman(HumanEnum.WhiteFemaleHuman);
    }
    //创建一个女性黄种人
    public Human createYellowHuman() {
        return super.createHuman(HumanEnum.YelloFemaleHuman);
    }
}
