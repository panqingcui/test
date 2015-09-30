/*
 * Copyright (c) 2013-2014 Shandong Antrol Network Technology Co.Ltd. All rights reserved. 版权所有(c) 2013-2014
 * 山东蚁巡网络科技有限公司。保留所有权利
 */
package cjy.com.mongodb;

import java.util.List;

import org.ietf.jgss.Oid;

/**
 * <p>功能描述,该部分必须以中文句号结尾。<p>
 * 
 * 创建日期 2014年11月4日<br>
 * @author $Author$<br>
 * @version $Revision$ $Date$
 * @since 3.0.0
 */
public class Student {
    private Oid _id;
    private String name;
    private List<DBref> classes;

    public Oid getId() {
        return _id;
    }

    public void setId(Oid _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<DBref> getDb() {
        return classes;
    }

    public void setDb(List<DBref> classes) {
        this.classes = classes;
    }
}
