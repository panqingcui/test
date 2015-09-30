package cjy.com.singleton;

/**
 * 枚举实现的Singleton
 * 
 * @author 老紫竹(laozizhu.com)
 */
public enum TestSingleton3 {
    INSTANCE;
    public static TestSingleton3 getInstance() {
        return INSTANCE;
    }
}
