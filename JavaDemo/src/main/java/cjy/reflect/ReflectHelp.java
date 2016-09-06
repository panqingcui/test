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
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * <p>功能描述,该部分必须以中文句号结尾。<p>
 * 
 * 创建日期 2014年1月17日<br>
 * @author $Author$<br>
 * @version $Revision$ $Date$
 * @since 3.0.0
 */
public class ReflectHelp {
    /**
     * 通过构造器取得实例
     * @param className 类的全限定名
     * @param intArgsClass 构造函数的参数类型
     * @param intArgs 构造函数的参数值
     * 
     * @return Object
     */
    public static Object getObjectByConstructor(String className, Class[] intArgsClass, Object[] intArgs) {
        Object returnObj = null;
        try {
            Class classType = Class.forName(className);
            Constructor constructor = classType.getDeclaredConstructor(intArgsClass); // 找到指定的构造方法
            constructor.setAccessible(true);// 设置安全检查，访问私有构造函数必须
            returnObj = constructor.newInstance(intArgs);
        } catch (NoSuchMethodException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return returnObj;
    }

    /**
     * 修改成员变量的值
     * @param Object 修改对象
     * @param filedName 指定成员变量名
     * @param filedValue 修改的值
     * 
     * @return
     */
    public static void modifyFileValue(Object object, String filedName, String filedValue) {
        Class classType = object.getClass();
        Field fild = null;
        try {
            fild = classType.getDeclaredField(filedName);
            fild.setAccessible(true);// 设置安全检查，访问私有成员变量必须
            fild.set(object, filedValue);
        } catch (NoSuchFieldException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 访问类成员变量
     * @param Object 访问对象
     * @param filedName 指定成员变量名
     * @return Object 取得的成员变量的值
     * */
    public static Object getFileValue(Object object, String filedName) {
        Class classType = object.getClass();
        Field fild = null;
        Object fildValue = null;
        try {
            fild = classType.getDeclaredField(filedName);
            fild.setAccessible(true);// 设置安全检查，访问私有成员变量必须
            fildValue = fild.get(object);
        } catch (NoSuchFieldException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return fildValue;
    }

    /**
     * 调用类的方法，包括私有
     * @param Object 访问对象
     * @param methodName 指定成员变量名
     * @param type 方法参数类型
     * @param value 方法参数指
     * @return Object 方法的返回结果对象
     * */
    public static Object useMethod(Object object, String methodName, Class[] type, Class[] value) {
        Class classType = object.getClass();
        Method method = null;
        Object fildValue = null;
        try {
            method = classType.getDeclaredMethod(methodName, type);
            method.setAccessible(true);// 设置安全检查，访问私有成员方法必须
            fildValue = method.invoke(object, value);
        } catch (NoSuchMethodException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return fildValue;
    }

    public static void main(String[] args) {
        RunBing runbing = new RunBing();
        runbing.setName("aaa");
        // 访问成员变量
        String value = (String) ReflectHelp.getFileValue(runbing, "sex");
        System.out.println("value:" + value);// 获取sex 值
        // 修改成员变量
        ReflectHelp.modifyFileValue(runbing, "sex", "bbb");
        System.out.println("value:" + runbing.getName());
        // 检查修改后的变量值
        value = (String) ReflectHelp.getFileValue(runbing, "sex");
        System.out.println("value:" + value);
        // 调用方法
        value = (String) ReflectHelp.useMethod(runbing, "add", new Class[] {}, new Class[] {});
        System.out.println("value:" + value);
        // 使用指定构造函数
        Class[] inArgs = new Class[] {String.class };
        Object[] inArgsParms = new Object[] {"hanj" };
        RunBing runBing2 = (RunBing) ReflectHelp.getObjectByConstructor("cjy.reflect.RunBing", inArgs, inArgsParms);
        value = (String) ReflectHelp.getFileValue(runBing2, "name");
        System.out.println("cc -- value:" + value);
    }
}

class RunBing {
    private String name;
    private int age;
    public String sex;

    public RunBing() {}

    private RunBing(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String run() {
        return "run method";
    }

    private String add() {
        return "add method";
    }
}
