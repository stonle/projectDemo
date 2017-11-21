package com.java.pattern.factory;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;

/**
 * Created by Administrator on 2017/11/4.
 *
 * 设计模式之四：工厂方法模式
 *
 *  工厂方法模式： 简单工厂模式，也是静态工厂模式
 *  Product(抽象产品)：封装了各种产品对象的公有方法，比如这里的Human
 *  ConcreteProduct(具体产品)：抽象产品的具体化，比如这里的珍珠和椰果YellowHuman、WhiteHuman;
 *  Factory(工厂)：实现创建所有产品实例的内部逻辑，比如这里的HumanFactory ；
 */
public class FactorPattern {
    public static void main(String[] args) {
/*        //女娲第一次造人，试验性质，少造点，火候不足，缺陷产品
        System.out.println("------------造出的第一批人是这样的：白人-----------------");
        SHumanFactory sHumanFactory = new HumanFactory();
        Human whiteHuman = sHumanFactory.createHuman(WhiteHuman.class);
        whiteHuman.cry();
        whiteHuman.laugh();
        whiteHuman.talk();
        //女娲第二次造人，火候加足点，然后又出了个次品，黑人
        System.out.println("\n\n------------造出的第二批人是这样的：黑人-----------------");
        Human blackHuman = sHumanFactory.createHuman(BlackHuman.class);
        blackHuman.cry();
        blackHuman.laugh();
        blackHuman.talk();
        //第三批人了，这次火候掌握的正好，黄色人种（不写黄人，免得引起歧义），备注：RB人不属于此列
        System.out.println("\n\n------------造出的第三批人是这样的：黄色人种-----------------");

        Human yellowHuman = sHumanFactory.createHuman(YellowHuman.class);
        yellowHuman.cry();
        yellowHuman.laugh();
        yellowHuman.talk();*/
        for(int i=0;i<1000000;i++){
            System.out.println("\n\n------------随机产生人种了-----------------" +
                    i);
            Human human = HumanFactory1.createHuman();
            human.cry();
            human.laugh();
            human.talk();
        }
    }
}

interface  Human{
    // 首先定义什么是人类
    // 人是愉快的，会笑的，本来是想用smile表示，想了一下laugh更合适，好长时间没有大笑了；
     void laugh();

    // 人类还会哭，代表痛苦
     void cry();

    // 人类会说话
     void talk();
}

class  YellowHuman  implements Human{

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

class WhiteHuman  implements  Human{

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

class BlackHuman  implements Human{

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

//工厂模式一
class  HumanFactory1{
    public static Human createHuman(Class c){
        Human human=null; //定义一个类型的人类
        try {
            human = (Human) Class.forName(c.getName()).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return human;
    }

    //改进方式二
    public static Human createHuman(){
        Human human=null; //定义一个类型的人类
        //首先是获得有多少个实现类，多少个人种
        List<Class> concreteHumanList =
                ClassUtils.getAllClassByInterface(Human.class); //定义了多少人种
        //八卦炉自己开始想烧出什么人就什么人
        Random random = new Random();
        int rand = random.nextInt(concreteHumanList.size());
        human = createHuman(concreteHumanList.get(rand));
        return human;
    }
}
class ClassUtils{
    public static List<Class> getAllClassByInterface(Class c){
        List<Class> returnClassList = new ArrayList<Class>(); //返回结果
        //如果不是一个接口，则不做处理
        if(c.isInterface()){
            String packageName = c.getPackage().getName(); //获得当前的包名
            try {
                List<Class> allClass = getClasses(packageName); //获得当前包下及子包下的所有类
               //判断是否是同一个接口
                for(int i=0;i<allClass.size();i++){
                    if(c.isAssignableFrom(allClass.get(i))){ //判断是不是一个接口
                        if(!c.equals(allClass.get(i))){ //本身不加进去
                            returnClassList.add(allClass.get(i));
                        }
                    }
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return returnClassList;
    }
    //从一个包中查找出所有的类，在jar包中不能查找
    private static List<Class> getClasses(String packageName)
            throws ClassNotFoundException, IOException {
        ClassLoader classLoader = Thread.currentThread()
                .getContextClassLoader();
        String path = packageName.replace('.', '/');
        Enumeration<URL> resources = classLoader.getResources(path);
        List<File> dirs = new ArrayList<File>();
        while (resources.hasMoreElements()) {
            URL resource = resources.nextElement();
            dirs.add(new File(resource.getFile()));
        }
        ArrayList<Class> classes = new ArrayList<Class>();
        for (File directory : dirs) {
            classes.addAll(findClasses(directory, packageName));
        }
        return classes;
    }
    private static List<Class> findClasses(File directory, String packageName)
            throws ClassNotFoundException {
        List<Class> classes = new ArrayList<Class>();
        if (!directory.exists()) {
            return classes;
        }
        File[] files = directory.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                assert !file.getName().contains(".");
                classes.addAll(findClasses(file, packageName + "." +
                        file.getName()));
            } else if (file.getName().endsWith(".class")) {
                classes.add(Class.forName(packageName + '.' +
                        file.getName().substring(0, file.getName().length() - 6)));
            }
        }
        return classes;
    }
}
//工厂方式三
abstract  class  SHumanFactory{
    // 定一个烤箱，泥巴塞进去，人就出来，这个太先进了
    abstract <T extends  Human> T createHuman(Class<T> tClass);
}

//工厂方式四、工厂方法模式还有一个非常重要的应用，就是延迟始化(Lazy initialization)
class HumanFactory4{
    private static HashMap<String,Human> humans = new HashMap<String,Human>();

    public static Human createHuman(Class c){
        Human human = null;
        if(humans.containsKey(c.getSimpleName())){
            human = humans.get(c.getSimpleName());
        }else{
            try {
                human = (Human)Class.forName(c.getName()).newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            // 放到 MAP 中
            humans.put(c.getSimpleName(), human);
        }
        return human;
    }
}


/**
 * 通过反射简洁生产过程
 *
 */
class HumanFactory extends  SHumanFactory{
    @Override
    public <T extends Human> T createHuman(Class<T> tClass) {

        Human human = null; // 定义一个类型的人类
        try {
            human = (Human) Class.forName(tClass.getName()).newInstance(); // 产生一个人种
        } catch (InstantiationException e) {// 你要是不说个人种颜色的话，没法烤，要白的黑，你说话了才好烤
            System.out.println("必须指定人种的颜色");
        } catch (IllegalAccessException e) { // 定义的人种有问题，那就烤不出来了，这是...
            System.out.println("人种定义错误！");
        } catch (ClassNotFoundException e) { // 你随便说个人种，我到哪里给你制造去？！
            System.out.println("混蛋，你指定的人种找不到！");
        }
        return (T)human;
    }

}



