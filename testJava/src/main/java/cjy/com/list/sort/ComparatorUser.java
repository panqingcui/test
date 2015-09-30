package cjy.com.list.sort;

import java.util.Comparator;

/**
 * <p>具体的比较类，实现Comparator接口。<p>
 * 
 * 创建日期 2012-11-23<br>
 * @author 陈军营(chenjunying@antvision.cn) <br>
 * @version $Revision$ $Date$
 * @since 3.0.0
 */
public class ComparatorUser implements Comparator<User> {
    public int compare(User user0, User user1) {
        // 首先比较年龄，如果年龄相同，则比较名字
        int flag = user0.getAge().compareTo(user1.getAge());
        if (flag == 0) {
            return user0.getName().compareTo(user1.getName());
        } else {
            return flag;
        }
    }
}
