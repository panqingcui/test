package cjy.map;

public class User {
    private String username;
    private int age;

    public User(String username, int age) {
        this.username = username;
        this.age = age;
    }

    @Override
    public String toString() {
        return "User [username=" + username + ", age=" + age + "]";
    }

    public void setUserName(String username) {
        this.username = username;
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
