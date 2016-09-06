package cjy.reflect;

import java.lang.reflect.Constructor;

/**
 * 
 * <p>通过构造方法时，Person中不能有set方法。 通过Class调用其他类中的构造函数 （也可以通过这种方式通过Class创建其他类的对象） <p>
 * 
 * 创建日期 2014年1月14日<br>
 * @author $Author$<br>
 * @version $Revision$ $Date$
 * @since 3.0.0
 */
interface China {
    public static final String name = "Rollen";
    public static int age = 20;

    public void sayChina();

    public void sayHello(String name, int age);
}

public class Demo_004 {
    public static void main(String[] args) {
        Class<?> demo = null;
        try {
            demo = Class.forName("cjy.reflect.Person_004");
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 保存所有的接口
        Class<?> intes[] = demo.getInterfaces();
        for (int i = 0; i < intes.length; i++) {
            System.out.println("实现的接口   " + intes[i].getName());
        }
        Class<?> temp = demo.getSuperclass();
        System.out.println("父类：" + temp.getName());
        Constructor<?>[] structor = demo.getConstructors();
        for (int i = 0; i < structor.length; i++) {
            System.out.println("构造方法：" + structor[i]);
        }
    }
}
