package cjy.com.redis;

import java.util.HashMap;
import java.util.Map;

public class User {
    //
    @SuppressWarnings("unused")
    private static final long serialVersionUID = 1L;
    private String name;// 姓名
    private int age;// 年龄
    private Map<String, Address> map = new HashMap<String, Address>();
    private static User uniqueInstance = null;

    public static User getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new User();
        }
        return uniqueInstance;
    }

    public Map<String, Address> getMap() {
        return map;
    }

    public void setMap(Map<String, Address> map) {
        this.map = map;
    }

    public User() {}

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
