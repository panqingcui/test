package cjy.map;

import java.util.Vector;

public class User {
    private String username;
    private int age;
    private Vector vector;

    public User(String username, int age) {
        this.username = username;
        this.age = age;
    }

    @SuppressWarnings("rawtypes")
    public Vector getVector() {
        return this.vector;
    }

    public void setVector(Vector<?> vector) {
        this.vector = vector;
    }

    public void setUserName(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "User [username=" + username + ", age=" + age + ", vector=" + vector + "]";
    }

    public String getUserName() {
        return this.username;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAge() {
        return this.age;
    }
}
