package cjy.com.singleton;

import java.lang.reflect.Constructor;

/**
 * 测试Singleton的可靠性。
 * 
 * @author 老紫竹(laozizhu.com)
 */
public class TestSingleton {
    public static void main(String[] args) {
        testSingleton1();
        testSingleton2();
        testSingleton3();
    }

    @SuppressWarnings("rawtypes")
    public static void testSingleton1() {
        try {
            // 测试Singletom1
            // 拿到第一个实例
            TestSingleton1 s1 = TestSingleton1.getInstance();
            // 测试拿到第二个实例
            Class c1 = Class.forName("cjy.com.singleton.TestSingleton1");
            Constructor[] cons = c1.getDeclaredConstructors();
            Constructor cc1 = cons[0];
            cc1.setAccessible(true);
            TestSingleton1 s2 = (TestSingleton1) cc1.newInstance();
            System.out.println(s1 + "/" + s2);
            System.out.println(s1 == s2);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @SuppressWarnings("rawtypes")
    public static void testSingleton2() {
        try {
            // 测试Singletom1
            // 拿到第一个实例
            TestSingleton2 s1 = TestSingleton2.getInstance();
            // 测试拿到第二个实例
            Class c1 = Class.forName("cjy.com.singleton.TestSingleton2");
            Constructor[] cons = c1.getDeclaredConstructors();
            Constructor cc1 = cons[0];
            cc1.setAccessible(true);
            TestSingleton2 s2 = (TestSingleton2) cc1.newInstance();
            System.out.println(s1 + "/" + s2);
            System.out.println(s1 == s2);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @SuppressWarnings("rawtypes")
    public static void testSingleton3() {
        try {
            // 测试Singletom1
            // 拿到第一个实例
            TestSingleton3 s1 = TestSingleton3.getInstance();
            // 测试拿到第二个实例
            Class c1 = Class.forName("cjy.com.singleton.TestSingleton3");
            Constructor[] cons = c1.getDeclaredConstructors();
            Constructor cc1 = cons[0];
            cc1.setAccessible(true);
            TestSingleton3 s2 = (TestSingleton3) cc1.newInstance();
            System.out.println(s1 + "/" + s2);
            System.out.println(s1 == s2);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
