package cjy.reflect;

import java.lang.reflect.Field;

/**
 * 
 * <p>通过反射操作属性。<p>
 * 
 * 创建日期 2014年1月16日<br>
 * @author $Author$<br>
 * @version $Revision$ $Date$
 * @since 3.0.0
 */
public class Demo_010 {
    public static void main(String[] args) {
        Class<?> demo = null;
        Object obj = null;
        try {
            demo = Class.forName("cjy.reflect.Person_008");
            Field field = demo.getDeclaredField("name");
            obj = demo.newInstance();
            field.setAccessible(true);
            field.set(obj, "男");
            System.out.println(field.get(obj));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }
}
