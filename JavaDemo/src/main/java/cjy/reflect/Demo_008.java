package cjy.reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 
 * <p>获取构造方法参数<p>
 * 
 * 创建日期 2014年1月14日<br>
 * @author $Author$<br>
 * @version $Revision$ $Date$
 * @since 3.0.0
 */
public class Demo_008 {
    public static void main(String[] args) {
        Class<?> demo = null;
        try {
            demo = Class.forName("cjy.reflect.Person_008");
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 获取方法
        try {
            // 调用1、
            // Class<?> c = demo.getClass();
            // Class[] cl = new Class[2];
            // cl[0] = "李四".getClass();
            // Integer in = new Integer(2);
            // cl[1] = in.getClass();
            // Method method = demo.getMethod("sayHello", cl);
            // 调用2、
            Method method = demo.getMethod("sayHello", String.class, int.class);
            // 带有指定参数的指定对象调用由此 Method 对象表示的底层方法
            method.invoke(demo.newInstance(), "李四", 3);//
            Method m = demo.getMethod("sayChina");
            m.invoke(demo.newInstance());
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }
}
