package cjy.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.annotation.Resource;

@Resource
abstract public class Demo_017 {
    public static void main(String[] args) throws InstantiationException {
        Class<?> demo = null;
        try {
            demo = Class.forName("cjy.reflect.MyReflect");
            Constructor con = MyReflect.class.getDeclaredConstructor(int.class);// 获取构造方法
            con.setAccessible(true);
            Object obj = con.newInstance(5);
            Method method = obj.getClass().getMethod("increase", int.class);
            method.invoke(obj, 5);// 调用方法
            Field field = obj.getClass().getDeclaredField("count");
            field.setAccessible(true);
            System.out.println(field.getInt(obj));
            // Constructor[] con = demo.getConstructors();
            // for (int i = 0; i < con.length; i++) {
            // int modify = con[i].getModifiers();
            // System.out.print("" + Modifier.toString(modify) + "  ");
            // System.out.print(con[i].getName());
            // Class[] c = con[i].getParameterTypes();
            // System.out.print("(");
            // for (int j = 0; j < con.length; j++) {
            // System.out.print("" + c[j].getName() + "  args" + j);
            // if (j < con.length - 1) {
            // System.out.print(",");
            // }
            // }
            // System.out.print(")");
            // }
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}

class MyReflect {
    private int count;

    private MyReflect(int a) {
        count = a;
    }

    public void increase(int step) {
        count = count + step;
    }
}
