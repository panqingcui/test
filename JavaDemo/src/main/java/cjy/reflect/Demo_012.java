package cjy.reflect;

import java.lang.reflect.Array;

/**
 * 
 * <p>通过反射修改数组大小。<p>
 * 
 * 创建日期 2014年1月16日<br>
 * @author $Author$<br>
 * @version $Revision$ $Date$
 * @since 3.0.0
 */
public class Demo_012 {
    public static void main(String[] args) {
        int temp[] = {1, 2, 3, 4 };
        int newTemp[] = (int[]) modifyInc(temp, 8);
        print(newTemp);
        System.out.println("=====================");
        String[] atr = {"a", "b", "c" };
        String[] str1 = (String[]) modifyInc(atr, 8);
        print(str1);
    }

    /**
     * 调整数据大小
     * @param obj
     * @param len
     */
    public static Object modifyInc(Object obj, int len) {
        // 获取数组
        Class<?> array = obj.getClass().getComponentType();
        Object newArray = Array.newInstance(array, len);// 新数组
        int a = Array.getLength(obj);// 数组长度
        System.arraycopy(obj, 0, newArray, 1, a);
        return newArray;
    }

    /**
     * 
     * @param obj
     */
    public static void print(Object obj) {
        Class<?> c = obj.getClass();
        if (!c.isArray()) {
            return;
        }
        System.out.println("数组长度为： " + Array.getLength(obj));
        for (int i = 0; i < Array.getLength(obj); i++) {
            System.out.print(Array.get(obj, i) + " ");
        }
    }
}
