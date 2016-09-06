package cjy.reflect;

import java.lang.reflect.Method;
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
public class Demo_006 implements China {
    public static void main(String[] args) {
        Class<?> demo = null;
        try {
            demo = Class.forName("cjy.reflect.Person_006");
        } catch (Exception e) {
            e.printStackTrace();
        }
        Method method[] = demo.getMethods();
        for (int i = 0; i < method.length; ++i) {
            Class<?> returnType = method[i].getReturnType();
            Class<?> para[] = method[i].getParameterTypes();
            int temp = method[i].getModifiers();
            System.out.print(Modifier.toString(temp) + " ");
            System.out.print(returnType.getName() + "  ");
            System.out.print(method[i].getName() + " ");
            System.out.print("(");
            for (int j = 0; j < para.length; ++j) {
                System.out.print(para[j].getName() + " " + "arg" + j);
                if (j < para.length - 1) {
                    System.out.print(",");
                }
            }
            Class<?> exce[] = method[i].getExceptionTypes();
            if (exce.length > 0) {
                System.out.print(") throws ");
                for (int k = 0; k < exce.length; ++k) {
                    System.out.print(exce[k].getName() + " ");
                    if (k < exce.length - 1) {
                        System.out.print(",");
                    }
                }
            } else {
                System.out.print(")");
            }
            System.out.println();
        }
    }

    @Override
    public void sayChina() {
        // TODO Auto-generated method stub
    }

    @Override
    public void sayHello(String name, int age) {
        // TODO Auto-generated method stub
    }
}
