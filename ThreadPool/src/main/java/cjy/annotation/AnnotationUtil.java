/*
 * Copyright (c) 2010-2015 EEFUNG Software Co.Ltd. All rights reserved.
 * 版权所有(c)2010-2015湖南蚁坊软件有限公司。保留所有权利
 */
package cjy.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>功能描述,该部分必须以中文句号结尾。<p>
 * 
 * 创建日期 2015年12月25日<br>
 * @author $Author$<br>
 * @version $Revision$ $Date$
 * @since 1.0.0
 */
public class AnnotationUtil {
    public static AnnotationUtil anno = null;

    public static AnnotationUtil getInstance() {
        if (anno == null) {
            anno = new AnnotationUtil();
        }
        return anno;
    }

    /**
     * 读取注解值
     * 
     * @param annotationClasss 处理Annotation类名称
     * @param annotationField 处理Annotation类属性名称
     * @param className 处理Annotation的使用类名称
     * @return
     * @throws Exception
     */
    @SuppressWarnings("all")
    public Map<String, String> loadVlaue(Class annotationClasss, String annotationField, String className)
            throws Exception {
        System.out.println("处理Annotation类名称  === " + annotationClasss.getName());
        System.out.println("处理Annotation类属性名称  === " + annotationField);
        System.out.println("处理Annotation的调用类名称  === " + className);
        Map<String, String> map = new HashMap<String, String>();
        Method[] methods = Class.forName(className).getDeclaredMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(annotationClasss)) {
                Annotation p = method.getAnnotation(annotationClasss);
                Method m = p.getClass().getDeclaredMethod(annotationField, null);
                String[] values = (String[]) m.invoke(p, null);
                for (String key : values) {
                    System.out.println("注解值 === " + key);
                    map.put(key, key);
                }
            }
        }
        System.out.println("map数量  === " + map.size());
        return map;
    }

    public static void main(String[] args) throws Exception {
        AnnotationUtil.getInstance().loadVlaue(Privilege.class, "value", TestPrivilege.class.getName());
    }
}
