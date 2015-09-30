package cjy.reflect;

import org.apache.log4j.Logger;

class Demo_002 {
    private static Logger logger = Logger.getLogger(Demo_002.class);

    public static void main(String[] args) {
        Class<?> demo1 = null;
        try {
            demo1 = Class.forName("cjy.reflect.Person_002");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Person_002 per = null;
        try {
            per = (Person_002) demo1.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        per.setAge(3);
        per.setName("name");
        logger.info("person对象:" + per);
    }
}
