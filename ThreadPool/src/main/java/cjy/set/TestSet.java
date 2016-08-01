package cjy.set;

import java.util.HashSet;
import java.util.Set;

/**
 * <p>功能描述,该部分必须以中文句号结尾。<p>
 *
 * 创建日期 2016年7月28日<br>
 * @author $Author$<br>
 * @version $Revision$ $Date$
 * @since 1.0.0
 */
public class TestSet {
    public static void main(String[] args) {
        Set set = new HashSet();
        System.out.println(set.add("1"));
        System.out.println(set.add("1"));
    }
}
