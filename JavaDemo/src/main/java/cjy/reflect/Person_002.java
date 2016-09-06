package cjy.reflect;

class Person_002 {
    public Person_002() {}

    public Person_002(String name) {
        this.name = name;
    }

    public Person_002(int age) {
        this.age = age;
    }

    public Person_002(String name, int age) {
        this.age = age;
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "[" + this.name + "  " + this.age + "]";
    }

    private String name;
    private int age;
}
