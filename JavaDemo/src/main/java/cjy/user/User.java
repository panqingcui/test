/*
 * Copyright (c) 2013-2014 Shandong Antrol Network Technology Co.Ltd.  All rights reserved.
 * 版权所有(c) 2013-2014 山东蚁巡网络科技有限公司。保留所有权利
 */
 
package cjy.user;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>功能描述,该部分必须以中文句号结尾。<p>
 *
 * 创建日期 2014年10月13日<br>
 * @author  $Author$<br>
 * @version $Revision$ $Date$
 * @since   3.0.0
 */
public class User {
    private Map<Integer, Address> ress = new HashMap<Integer, Address>();
    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Map<Integer, Address> getRess() {
        return ress;
    }

    public void setRess(Map<Integer, Address> ress) {
        this.ress = ress;
    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    private int age;
}
