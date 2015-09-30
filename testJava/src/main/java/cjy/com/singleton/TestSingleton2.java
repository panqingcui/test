package cjy.com.singleton;

/**
 * 一个用异常强化了的Singletone实现。
 * 
 * @author 老紫竹(laozizhu.com)
 */
public class TestSingleton2 {
    private static final TestSingleton2 INSTANCE = new TestSingleton2();

    public static TestSingleton2 getInstance() {
        return INSTANCE;
    }

    private static boolean initSign;

    private TestSingleton2() {
        if (initSign) {
            throw new RuntimeException("实例只能建造一次");
        }
        initSign = true;
    }
}
