/*
 * Copyright (c) 2010-2015 EEFUNG Software Co.Ltd. All rights reserved.
 * 版权所有(c)2010-2015湖南蚁坊软件有限公司。保留所有权利
 */
package cjy.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>功能描述,该部分必须以中文句号结尾。<p>
 * 
 * 创建日期 2015年12月25日<br>
 * @author $Author$<br>
 * @version $Revision$ $Date$
 * @since 1.0.0
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Privilege {
    String[] value();
}
