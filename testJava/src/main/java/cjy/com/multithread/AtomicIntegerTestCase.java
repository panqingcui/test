package cjy.com.multithread;

/**
 * 
 * @Described：原子int类型操作测试用例
 * 
 * @author YHJ create at 2013-4-26 下午05:58:32
 * 
 * @ClassNmae com.yhj.lock.AtomicIntegerTestCase
 */
public interface AtomicIntegerTestCase {
    /**
     * 
     * ++并返回
     * 
     * @return
     * 
     * @Author YHJ create at 2013-4-26 下午05:39:47
     */
    int incrementAndGet();

    /**
     * 
     * 取值
     * 
     * @return
     * 
     * @Author YHJ create at 2013-4-26 下午05:39:56
     */
    int get();
}
