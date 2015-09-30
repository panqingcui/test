package cjy.com.singleton;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * 枚举单例测试
 * @author 乔磊
 * 
 */
public class EnumTest {
    @SuppressWarnings("rawtypes")
    public static void singletonTest() throws IllegalArgumentException, InstantiationException, IllegalAccessException,
            InvocationTargetException {
        try {
            // 得到第一个实例
            SingletonExample s = SingletonExample.getInstance();
            // 用反射得到第二个实例,这里引用类的时候得写全路径，否则会报找不到类
            Class c = Class.forName("cjy.com.singleton.SingletonExample");
            // getDeclaredConstructors返回 Constructor 对象的一个数组，
            // 这些对象反映此 Class 对象表示的类声明的所有构造方法。
            // 它们是公共、保护、默认（包）访问和私有构造方法。
            // 返回数组中的元素没有排序，也没有任何特定的顺序。
            // 如果该类存在一个默认构造方法，则它包含在返回的数组中。
            // 如果此 Class 对象表示一个接口、一个基本类型、一个数组类或 void，
            // 则此方法返回一个长度为 0 的数组
            Constructor[] con = c.getDeclaredConstructors();
            Constructor conc = con[0];
            // setAccessible将此对象的 accessible 标志设置为指示的布尔值。
            // 值为 true 则指示反射的对象在使用时应该取消 Java 语言访问检查。
            // 值为 false 则指示反射的对象应该实施 Java 语言访问检查。
            conc.setAccessible(true);
            SingletonExample ss = (SingletonExample) conc.newInstance();
            System.out.println(s + "/" + ss);
            System.out.println(s == ss);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IllegalArgumentException, InstantiationException,
            IllegalAccessException, InvocationTargetException {
        singletonTest();
    }
}

enum SingletonExample {
    INSTANCE;
    public static SingletonExample getInstance() {
        return INSTANCE;
    }
}
