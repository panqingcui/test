package cjy.reflect;

import java.lang.reflect.Field;
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
public class Demo_007 {
    public static void main(String[] args) {
        Class<?> demo = null;
        try {
            demo = Class.forName("cjy.reflect.Person_006");
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("===============本类属性========================");
        // 取得本类的全部属性
        Field[] field = demo.getDeclaredFields();
        for (int i = 0; i < field.length; i++) {
            // 权限修饰符
            int mo = field[i].getModifiers();
            String priv = Modifier.toString(mo);
            // 属性类型
            Class<?> type = field[i].getType();
            System.out.println(priv + " " + type.getName() + " " + field[i].getName() + ";");
        }
        System.out.println("===============实现的接口或者父类的属性========================");
        Field[] f = demo.getFields();
        for (int i = 0; i < f.length; i++) {
            // 权限修饰
            int mo = f[i].getModifiers();
            String priv = Modifier.toString(mo);
            // 属性类型
            Class<?> type = f[i].getType();
            System.out.println(priv + " " + type.getName() + " " + f[i].getName());
        }
    }
}
