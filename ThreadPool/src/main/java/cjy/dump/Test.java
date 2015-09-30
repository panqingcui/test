package cjy.dump;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Test {
    public void getData() {
        Map<String, User> map = new HashMap<String, User>();
        Object[] array = new Object[1000000];
        for (int i = 0; i < 1000000; i++) {
            String d = new Date().toString();
            User p = new User(d, i);
            map.put(i + "rosen jiang", p);
            array[i] = p;
        }
    }

    public static void main(String[] args) {
        new Test().getData();
    }
}

class User {
    String name;
    int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
