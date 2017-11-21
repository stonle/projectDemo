package com.java.pattern.adapter;

import java.util.HashMap;
import java.util.Map;

/**
 * 适配器模式
 * Created by Administrator on 2017/11/20.
 */
public class AdapterPattern1 {
}
//目标接口
interface IUserInfo{
    //获得用户姓名
    public String getUserName();
    //获得家庭地址
    public String getHomeAddress();
    //手机号码，这个太重要，手机泛滥呀
    public String getMobileNumber();
    //办公电话，一般式座机
    public String getOfficeTelNumber();
    //这个人的职位是啥
    public String getJobPosition();
    //获得家庭电话，这个有点缺德，我是不喜欢打家庭电话讨论工作
    public String getHomeTelNumber();
}
class UserInfo implements IUserInfo {

    public String getHomeAddress() {
        System.out.println("这里是员工的家庭地址....");
        return null;
    }
    /*
    * 获得家庭电话号码
    */
    public String getHomeTelNumber() {
        System.out.println("员工的家庭电话是....");
        return null;
    }
    /*
    * 员工的职位，是部门经理还是小兵
    */
    public String getJobPosition() {
        System.out.println("这个人的职位是BOSS....");
        return null;
    }
    /*
    * 手机号码
    */
    public String getMobileNumber() {
        System.out.println("这个人的手机号码是0000....");
        return null;
    }
    /*
    * 办公室电话，烦躁的时候最好“不小心”把电话线踢掉，我经常这么干，对己对人都有好处
    */
    public String getOfficeTelNumber() {
        System.out.println("办公室电话是....");
        return null;
    }
    /*
    * 姓名了，这个老重要了
    */
    public String getUserName() {
        System.out.println("姓名叫做...");
        return null;
    }
}


interface IOuterUser{
    //基本信息，比如名称，性别，手机号码了等
    public Map getUserBaseInfo();
    //工作区域信息
    public Map getUserOfficeInfo();
    //用户的家庭信息
    public Map getUserHomeInfo();
}
class OuterUser implements IOuterUser {

    @Override
    public Map getUserBaseInfo() {
        HashMap baseInfoMap = new HashMap();
        baseInfoMap.put("userName", "这个员工叫混世魔王....");
        baseInfoMap.put("mobileNumber", "这个员工电话是....");
        return baseInfoMap;
    }

    @Override
    public Map getUserOfficeInfo() {
        HashMap homeInfo = new HashMap();
        homeInfo.put("homeTelNumbner", "员工的家庭电话是....");
        homeInfo.put("homeAddress", "员工的家庭地址是....");
        return homeInfo;
    }

    @Override
    public Map getUserHomeInfo() {
        HashMap officeInfo = new HashMap();
        officeInfo.put("jobPosition","这个人的职位是BOSS...");
        officeInfo.put("officeTelNumber", "员工的办公电话是....");
        return officeInfo;
    }
}
//适配器
class OuterUserInfo extends OuterUser implements IUserInfo {
    private Map baseInfo = super.getUserBaseInfo(); //员工的基本信息
    private Map homeInfo = super.getUserHomeInfo(); //员工的家庭 信息
    private Map officeInfo = super.getUserOfficeInfo(); //工作信息
    @Override
    public String getUserName() {
        String homeAddress = (String)this.homeInfo.get("homeAddress");
        System.out.println(homeAddress);
        return homeAddress;
    }

    @Override
    public String getHomeAddress() {
        String homeTelNumber = (String)this.homeInfo.get("homeTelNumber");
        System.out.println(homeTelNumber);
        return homeTelNumber;
    }

    @Override
    public String getMobileNumber() {
        String mobileNumber = (String)this.baseInfo.get("mobileNumber");
        System.out.println(mobileNumber);
        return mobileNumber;
    }

    @Override
    public String getOfficeTelNumber() {
        String officeTelNumber = (String)this.officeInfo.get("officeTelNumber");
        System.out.println(officeTelNumber);
        return officeTelNumber;
    }

    @Override
    public String getJobPosition() {
        String jobPosition = (String)this.officeInfo.get("jobPosition");
        System.out.println(jobPosition);
        return jobPosition;
    }

    @Override
    public String getHomeTelNumber() {
        String userName = (String)this.baseInfo.get("userName");
        System.out.println(userName);
        return userName;
    }
}