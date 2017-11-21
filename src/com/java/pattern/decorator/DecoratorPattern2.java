package com.java.pattern.decorator;

/**
 * decorator:装饰者模式，是结构型模式
 *  结构型模式主要用于描述如何实现类或对象的组合
 *
 *  你要知道继承是静态的给类增加功能，而装饰模式则是动态的给增加功能
 * Created by Administrator on 2017/11/21.
 */
public class DecoratorPattern2 {
    public static void main(String[] args) {
        //成绩单拿过来
        SchoolReport sr;
        sr = new FouthGradeSchoolReport(); //原装的成绩单
        //加 了最高分说明的成绩单
        sr = new HighScoreDecorator(sr);
        //又加了成绩排名的说明
        sr = new SortDecorator(sr);
         //看成绩单
        sr.report();
        //然后老爸，一看，很开心，就签名了
        sr.sign("老三"); //我叫小三，老爸当然叫老三
    }
}

//成绩单的抽象类
abstract class SchoolReport {
    //成绩单的主要展示的就是你的成绩情况
    public abstract void report();
    //成绩单要家长签字，这个是最要命的
    public abstract void sign(String name);
}
//然后看我们的实现类 FouthGradSchoolReport
class FouthGradeSchoolReport extends SchoolReport {
    //我的成绩单
    public void report() {
//成绩单的格式是这个样子的
        System.out.println("尊敬的XXX家长:");
        System.out.println(" ......");
        System.out.println(" 语文 62 数学65 体育 98 自然 63");
        System.out.println(" .......");
        System.out.println(" 家长签名： ");
    }
    //家长签名
    public void sign(String name) {
        System.out.println("家长签名为："+name);
    }
}
//装饰类，我要把我的成绩单装饰一下
abstract class Decorators extends SchoolReport {
    //首先我要知道是那个成绩单
    private SchoolReport sr;

    //构造函数，传递成绩单过来
    public Decorators(SchoolReport sr) {
        this.sr = sr;
    }
    //成绩单还是要被看到的
    public void report(){
        this.sr.report();
    }
    //看完毕还是要签名的
    public void sign(String name){
        this.sr.sign(name);
    }
}
//我要把我学校的最高成绩告诉老爸
class HighScoreDecorator extends Decorators {
    public HighScoreDecorator(SchoolReport sr) {
        super(sr);
    }
    //我要汇报最高成绩
    private void reportHighScore(){
        System.out.println("这次考试语文最高是75，数学是78，自然是80");
    }
    //最高成绩我要做老爸看成绩单前告诉他，否则等他一看，就抡起笤帚有揍我，我那还有机会说呀
    @Override
    public void report() {
        this.reportHighScore();
        super.report();
    }
}
class SortDecorator extends Decorators {
    //构造函数
    public SortDecorator(SchoolReport sr){
        super(sr);
    }
    //告诉老爸学校的排名情况
    private void reportSort(){
        System.out.println("我是排名第38名...");
    }
    //老爸看完成绩单后再告诉他，加强作用
    @Override
    public void report(){
        super.report();
        this.reportSort();
    }
}
