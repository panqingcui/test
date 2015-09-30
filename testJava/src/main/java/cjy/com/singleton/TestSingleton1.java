package cjy.com.singleton;

/**
 * 一个普通的Singletone实现。
 * 
 * @author 老紫竹(laozizhu.com)
 */
public class TestSingleton1 {
    private static final TestSingleton1 INSTANCE = new TestSingleton1();

    public static TestSingleton1 getInstance() {
        return INSTANCE;
    }

    private TestSingleton1() {}
}
