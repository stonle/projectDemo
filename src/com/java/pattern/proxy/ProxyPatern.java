package com.java.pattern.proxy;

import org.springframework.cglib.proxy.Callback;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 *
 * 结构型模式：共7中
 *       适配器模式、装饰器模式、代理模式、外观模式、桥接模式、组合模式、享元模式
 * Proxy: 代理模式   引用代理对象对象的方式来访问目标对象
 * 定义：为其他对象提供一种代理以控制对这个对象的访问
 * 3个角色：
 *   Subject：抽象对象，声明真实角色与代理角色的公共接口。
 *   RealSubject：真实对象，代理角色所代表的真实对象，最终引用对象。
 *   Proxy：代理对象，包含对真实对象的引用从而操作真实对象，
 *   相当于对真实对象进行封装。
 * Created by Administrator on 2017/11/10.
 */
public class ProxyPatern
{
    public static void main(String[] args) {
        /*Agent agent = new Agent();
        agent.fetchShoes();*/

/*        // 目标对象
        IUserDao2 target = new UserDao2();
        // 【原始的类型 class cn.itcast.b_dynamic.UserDao2】
        System.out.println(target.getClass());
        System.out.println(target.getClass().getClassLoader());
        System.out.println(target.getClass().getInterfaces());
        // 给目标对象，创建代理对象
        IUserDao2 proxy = (IUserDao2) new ProxyFactory(target).getProxyInstance();
        // class $Proxy0   内存中动态生成的代理对象
        System.out.println(proxy.getClass());
        // 执行方法   【代理对象】
        proxy.save();*/

       // 测试Cglib
        //目标对象
        UserDao3 target = new UserDao3();

        //代理对象
        UserDao3 proxy = (UserDao3)new ProxyFactory2(target).getProxyInstance();

        //执行代理对象的方法
        proxy.save();
    }
}
//抽象对象接口
interface FetchGoods{
    public void fetchShoes();
}
//真实对象
class Custom implements FetchGoods{
    @Override
    public void fetchShoes() {
        System.out.println("拿货");
    }
}
//创建代理对象，创建引用真实对象实例并访问其方法
class Agent implements FetchGoods{

    @Override
    public void fetchShoes() {
       Custom custom = new Custom();
       custom.fetchShoes();
       this.callCustom();
    }

    public void callCustom(){
        System.out.println("通知顾客来取件！");
    }
}


//Proxy模式 2
interface  KindWomen{
    //这种类型的女人能做什么事情呢？
    public void makeEyesWithMan(); //抛媚眼
    public void happyWithMan(); //happy what? You know that!
}

class PanJinLian implements KindWomen{

    @Override
    public void makeEyesWithMan() {
        System.out.println("潘金莲在和男人做那个.....");
    }

    @Override
    public void happyWithMan() {
        System.out.println("潘金莲抛媚眼");
    }
}
class WangPo implements KindWomen{

    private KindWomen kindWomen;
    public WangPo(){ //默认的话，是潘金莲的代理
        this.kindWomen = new PanJinLian();
    }

    //她可以是KindWomen的任何一个女人的代理，只要你是这一类型
    public WangPo(KindWomen kindWomen){
        this.kindWomen = kindWomen;
    }
    @Override
    public void makeEyesWithMan() {
        this.kindWomen.happyWithMan(); //自己老了，干不了，可以让年轻的代替
    }

    @Override
    public void happyWithMan() {
        this.kindWomen.makeEyesWithMan(); //王婆这么大年龄了，谁看她抛媚眼？！
    }
}

// demo3
/**
 * Java的三种代理模式
 *
 * 1、静态代理，需要定义接口或者父类,被代理对象与代理对象一起实现相同的接口或者是继承相同父类.
 *   静态代理总结:
 *    1.可以做到在不修改目标对象的功能前提下,对目标功能扩展.
 *    2.缺点:
 *         因为代理对象需要与目标对象实现一样的接口,所以会有很多代理类,类太多.同时,一旦接口增加方法,目标对象与代理对象都要维护.
 * */
//接口
interface IUserDao{
    void save();
}
//目标对象
class UserDao implements IUserDao{

    @Override
    public void save() {
        System.out.println("----已经保存数据!----");
    }
}
//代理对象,静态代理
class UserDaoProxy  implements IUserDao{
    //接收保存目标对象
    private IUserDao target;
    public UserDaoProxy(IUserDao target){
        this.target=target;
    }
    @Override
    public void save() {
        System.out.println("开始事务...");
        target.save();//执行目标对象的方法
        System.out.println("提交事务...");
    }
}

//动态代理
/**
 * 动态代理有以下特点:
 * 1.代理对象,不需要实现接口
 * 2.代理对象的生成,是利用JDK的API,动态的在内存中构建代理对象(需要我们指定创建代理对象/目标对象实现的接口的类型)
 * 3.动态代理也叫做:JDK代理,接口代理
 *
 * 总结：代理对象不需要实现接口,但是目标对象一定要实现接口,否则不能用动态代理
 */
//接口
interface IUserDao2{
    void save();
}
//目标对象
class UserDao2 implements IUserDao2{

    @Override
    public void save() {
        System.out.println("----已经保存数据!----");
    }
}

//可以利用java.lang.reflect.Proxy类和java.lang.reflect.InvocationHandler接口定义代理类的实现。
class ProxyFactory{
    //维护一个目标对象
    private Object target;
    public ProxyFactory(Object target){
        this.target=target;
    }
    //给目标对象生成代理对象 ，
    //使用的是JDK的 static Object newProxyInstance(ClassLoader loader, Class<?>[] interfaces,InvocationHandler h )
    public Object getProxyInstance(){
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(), new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("开始事务2");
                        //执行目标对象方法
                        Object returnValue = method.invoke(target, args);
                        System.out.println("提交事务2");
                        return returnValue;
                    }
                });
    }
}


// demo3 Cglib代理

/**
 * 上面的静态代理和动态代理模式都是要求目标对象是实现一个接口的目标对象,
 * 但是有时候目标对象只是一个单独的对象,并没有实现任何的接口,
 * 这个时候就可以使用以目标对象子类的方式类实现代理,这种方法就叫做:Cglib代理
 *
 * Cglib代理：Cglib代理,也叫作子类代理,它是在内存中构建一个子类对象从而实现对目标对象功能的扩展.
 *     1、JDK的动态代理有一个限制,就是使用动态代理的对象必须实现一个或多个接口,
 *        如果想代理没有实现接口的类,就可以使用Cglib实现.
 *     2、Cglib是一个强大的高性能的代码生成包,它可以在运行期扩展java类与实现java接口.
 *         它广泛的被许多AOP的框架使用,例如Spring AOP和synaop,为他们提供方法的interception(拦截)
 *     3、Cglib包的底层是通过使用一个小而块的字节码处理框架ASM来转换字节码并生成新的类.
 *         不鼓励直接使用ASM,因为它要求你必须对JVM内部结构包括class文件的格式和指令集都很熟悉.
 *
 * 使用：1.需要引入cglib的jar文件,但是Spring的核心包中已经包括了Cglib功能,所以直接引入pring-core-3.2.5.jar即可.
 *       2.引入功能包后,就可以在内存中动态构建子类
 *       3.代理的类不能为final,否则报错
 *       4.目标对象的方法如果为final/static,那么就不会被拦截,即不会执行目标对象额外的业务方法.
 */


/**
 * 目标对象,没有实现任何接口
 */
class UserDao3 {

    public void save() {
        System.out.println("----已经保存数据!----");
    }
}

/**
 * Cglib子类代理工厂
 * 对UserDao在内存中动态构建一个子类对象
 */
//或者实现 MethodInterceptor接口
class ProxyFactory2{
    //维护目标对象
    private Object target;
    public ProxyFactory2(Object target) {
        this.target = target;
    }
    //给目标对象创建一个代理对象
    public Object getProxyInstance(){
        //1、工具类
        Enhancer en = new Enhancer();
        //2.设置父类
        en.setSuperclass(target.getClass());
        //3.设置回调函数
        en.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                System.out.println("开始事务...");

                //执行目标对象的方法
                Object returnValue = method.invoke(target, objects);

                System.out.println("提交事务...");

                return returnValue;
            }
        });
        //4.创建子类(代理对象)
        return en.create();
    }

/*    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("开始事务...");

        //执行目标对象的方法
        Object returnValue = method.invoke(target, objects);

        System.out.println("提交事务...");

        return returnValue;
    }*/
}