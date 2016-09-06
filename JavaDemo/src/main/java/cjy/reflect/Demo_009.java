package cjy.reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 
 * <p>调用其他类的set和get方法<p>
 * 
 * 创建日期 2014年1月14日<br>
 * @author $Author$<br>
 * @version $Revision$ $Date$
 * @since 3.0.0
 */
public class Demo_009 {
    public static void main(String[] args) {
        Class<?> demo = null;
        Object obj = null;
        try {
            demo = Class.forName("cjy.reflect.Person_008");
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 获取方法
        try {
            obj = demo.newInstance();
            setter(obj, "Name", "男", String.class);
            getter(obj, "Name");
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param obj 操作的对象
     * @param att 操作的属性
     * */
    public static void getter(Object obj, String str) {
        try {
            Method method = obj.getClass().getMethod("get" + str);
            System.out.println(method.invoke(obj));
            System.gc();
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
        }
    }

    /**
     * @param obj 操作的对象
     * @param att 操作的属性
     * @param value 设置的值
     * @param type 参数的属性
     * */
    public static void setter(Object obj, String att, Object value, Class<?> type) {
        try {
            Method method = obj.getClass().getMethod("set" + att, type);
            method.invoke(obj, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
