package cjy.reflect;

import java.lang.reflect.Constructor;

import org.apache.log4j.Logger;

public class Demo_003 {
    private static Logger logger = Logger.getLogger(Demo_003.class);

    public static void main(String[] args) {
        Class<?> demo = null;
        try {
            demo = Class.forName("cjy.reflect.Person_003");
        } catch (Exception e) {
            e.printStackTrace();
        }
        Person_003 per1 = null;
        Person_003 per2 = null;
        Person_003 per3 = null;
        Person_003 per4 = null;
        Constructor<?> cons[] = demo.getConstructors();
        try {
            per1 = (Person_003) cons[0].newInstance();
            per2 = (Person_003) cons[1].newInstance("Rollen");
            per3 = (Person_003) cons[2].newInstance(20);
            per4 = (Person_003) cons[3].newInstance("Rollen", 20);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(per1);
        System.out.println(per2);
        System.out.println(per3);
        System.out.println(per4);
    }
}
