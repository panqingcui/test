package cjy.user;

import java.io.Serializable;

public class Book implements Serializable {
    private static final long serialVersionUID = -564380176443249810L;
    private String name;
    private int age;

    public Book() {}

    public Book(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Book(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    // public void setAge(int age) {
    // this.age = age;
    // }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
