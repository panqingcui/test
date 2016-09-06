package cjy.test;

public class User {
    private String name;
    private String id;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return " [name=" + name + ", id=" + id + "]";
    }

    public String getId() {
        return id;
    }

    public User() {}

    public User(String name, String id) {
        this.name = name;
        this.id = id;
    }
}
