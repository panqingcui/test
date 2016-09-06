package cjy.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

/**
 * 
 * <p>获取构造方法参数<p>
 * 
 * 创建日期 2014年1月14日<br>
 * @author $Author$<br>
 * @version $Revision$ $Date$
 * @since 3.0.0
 */
public class Demo_005 {
    public static void main(String[] args) {
        Class<?> demo = null;
        try {
            demo = Class.forName("cjy.reflect.Person_005");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Constructor<?>[] con = demo.getConstructors();// 获取全部构造方法
        for (int i = 0; i < con.length; i++) {
            System.out.print("构造方法：");
            Class<?>[] types = con[i].getParameterTypes();// 方法参数元素
            int modifier = con[i].getModifiers();
            System.out.print(Modifier.toString(modifier) + "  ");
            System.out.print(con[i].getName());// 方法名
            System.out.print("(");
            for (int j = 0; j < types.length; j++) {
                System.out.print(types[j].getName() + "  args" + i);
                if (j < types.length) {
                    System.out.print(",");
                }
            }
            System.out.println("){}");
        }
    }
}
