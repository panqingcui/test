package cjy.reflect;

class Factory {
    public static fruit getInstance(String className) {
        fruit f = null;
        try {
            f = (fruit) Class.forName(className).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return f;
    }
}

public class Demo_015 {
    public static void main(String[] args) {
        Factory factory = new Factory();
        fruit f = factory.getInstance("cjy.reflect.Apple");
        f.eat();
    }
}
