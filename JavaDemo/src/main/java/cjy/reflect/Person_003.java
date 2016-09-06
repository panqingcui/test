package cjy.reflect;

class Person_003 {
    public Person_003() {}

    public Person_003(String name) {
        this.name = name;
    }

    public Person_003(int age) {
        this.age = age;
    }

    public Person_003(String name, int age) {
        this.age = age;
        this.name = name;
    }

    public String getName() {
        return name;
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
