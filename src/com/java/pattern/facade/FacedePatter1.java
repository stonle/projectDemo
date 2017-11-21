package com.java.pattern.facade;

/**
 * 门面模式
 * Created by Administrator on 2017/11/20.
 */
public class FacedePatter1 {
}
//定义一个写信的过程
interface LetterProcess {
    //首先要写信的内容
    public void writeContext(String context);
    //其次写信封
    public void fillEnvelope(String address);
    //把信放到信封里
    public void letterInotoEnvelope();
    //然后邮递
    public void sendLetter();
}
//具体实现
class LetterProcessImpl implements LetterProcess {
    //写信
    public void writeContext(String context) {
        System.out.println("填写信的内容...." + context);
    }
    //在信封上填写必要的信息
    public void fillEnvelope(String address) {
        System.out.println("填写收件人地址及姓名...." + address);
    }
    //把信放到信封中，并封好
    public void letterInotoEnvelope() {
        System.out.println("把信放到信封中....");
    }
    //塞到邮箱中，邮递
    public void sendLetter() {
        System.out.println("邮递信件...");
    }
}

class ModenPostOffice{
    private LetterProcess letterProcess = new LetterProcessImpl();

   // private Police letterPolice = new Police();
    //写信，封装，投递，一体化了
    public void sendLetter(String context,String address){
        //帮你写信
        letterProcess.writeContext(context);
         //写好信封
        letterProcess.fillEnvelope(address);
        //警察要检查信件了
       // letterPolice.checkLetter(letterProcess);
         //把信放到信封中
        letterProcess.letterInotoEnvelope();
        //邮递信件
        letterProcess.sendLetter();
    }
}