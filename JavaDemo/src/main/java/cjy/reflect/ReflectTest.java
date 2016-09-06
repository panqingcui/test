/*
 * Copyright (c) ANTVISION 2011 All Rights Reserved Licensed under the Apache License, Version 2.0 (the "License"); you
 * may not use this file except in compliance with the License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0 Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language governing permissions and limitations under the
 * License.
 */
package cjy.reflect;

import java.lang.reflect.Constructor;

/**
 * <p>功能描述,该部分必须以中文句号结尾。<p>
 * 
 * 创建日期 2014年1月17日<br>
 * @author $Author$<br>
 * @version $Revision$ $Date$
 * @since 3.0.0
 */
// 请注意执行顺序问题，开始时，我测试时用的是MyEclipse中的Jdk1.5.Jdk1.6我发现他们Constructor的顺序不一样的。
public class ReflectTest {
    String s;
    int i, i2, i3;

    protected ReflectTest() {
        s = "";
        i = 0;
        i2 = 0;
        i3 = 0;
    }

    protected ReflectTest(String s, int i) {
        this.s = s;
        this.i = i;
    }

    public ReflectTest(String... strings) throws NumberFormatException // 可变数量的参数
    {
        if (0 < strings.length) {
            i = Integer.valueOf(strings[0]);
        }
        if (1 < strings.length) {
            i2 = Integer.valueOf(strings[1]);
        }
        if (2 < strings.length) {
            i3 = Integer.valueOf(strings[2]);
        }
    }

    public void print() {
        System.out.println("s= " + s);
        System.out.println("i=" + i);
        System.out.println("i2= " + i2);
        System.out.println("i3=" + i3);
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Constructor[] declaredConstructor = ReflectTest.class.getDeclaredConstructors();// 获得所有的构造方法
        for (int i = 0; i < declaredConstructor.length; i++) {
            Constructor constructor = declaredConstructor[i];
            System.out.println("查看是否允许带有可变数量的参数:" + constructor.isVarArgs());// 是否允许带有可变数量的参数
            System.out.println("该构造方法的入口参数类型依次为：");
            Class[] parameterTypes = constructor.getParameterTypes();
            for (int j = 0; j < parameterTypes.length; j++)// 构造方法的入口参数类型
            {
                System.out.println("parameterTypes[" + j + "]" + parameterTypes[j]);
            }
            System.out.println("该构造方法可能抛出的异常类型：");
            Class[] exceptionTypes = constructor.getExceptionTypes();
            for (int j = 0; j < exceptionTypes.length; j++)// 构造方法可能抛出的异常类型
            {
                System.out.println("exceptionTypes[" + j + "]" + exceptionTypes[j]);
            }
            ReflectTest reflectTest = null;
            while (reflectTest == null) // 调用类中的构造方法
            {
                try {
                    if (i == 0) // 请注意执行顺序，可以先把这些语句注释掉，看下顺序再执行下面的代码.
                    {
                        reflectTest = (ReflectTest) constructor.newInstance();
                    } else if (i == 2) {
                        reflectTest = (ReflectTest) constructor.newInstance("7", 5);
                    } else if (i == 1) {
                        Object[] parameters = new Object[] {new String[] {"100", "200", "300" } };
                        // Object []parameters = new String[]{"100","200","300"};
                        reflectTest = (ReflectTest) constructor.newInstance(parameters);
                    }
                } catch (Exception e) {
                    System.out.println("在创建对象时抛出异常：下面执行setAccessible");
                    e.printStackTrace();
                    constructor.setAccessible(true);
                }
            }
            reflectTest.print();
            System.out.println();
            System.out.println("*********************");
        }
    }

    /**
     * @return the s
     */
    protected String getS() {
        return s;
    }

    /**
     * @param s the s to set
     */
    protected void setS(String s) {
        this.s = s;
    }

    /**
     * @return the i
     */
    protected int getI() {
        return i;
    }

    /**
     * @param i the i to set
     */
    protected void setI(int i) {
        this.i = i;
    }

    /**
     * @return the i2
     */
    protected int getI2() {
        return i2;
    }

    /**
     * @param i2 the i2 to set
     */
    protected void setI2(int i2) {
        this.i2 = i2;
    }

    /**
     * @return the i3
     */
    protected int getI3() {
        return i3;
    }

    /**
     * @param i3 the i3 to set
     */
    protected void setI3(int i3) {
        this.i3 = i3;
    }
}
