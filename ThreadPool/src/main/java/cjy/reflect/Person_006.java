package cjy.reflect;

class Person_006 implements China {
    public Person_006() {}

    public Person_006(String sex) {
        this.sex = sex;
    }

    public Person_006(String name, String sex) {
        this.name = name;
        this.sex = sex;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void sayChina() {
        System.out.println("hello ,china");
    }

    public void sayHello(String name, int age) {
        System.out.println(name + "  " + age);
    }

    private String sex;
    private String name;
}
