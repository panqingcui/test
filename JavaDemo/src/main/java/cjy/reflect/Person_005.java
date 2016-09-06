package cjy.reflect;

class Person_005 implements China {
    public Person_005() {}

    public Person_005(String sex) {
        this.sex = sex;
    }

    public Person_005(String name, String sex) {
        this.name = name;
        this.sex = sex;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public void sayChina() {
        System.out.println("hello ,china");
    }

    @Override
    public void sayHello(String name, int age) {
        System.out.println(name + "  " + age);
    }

    private String sex;
    private String name;
}
