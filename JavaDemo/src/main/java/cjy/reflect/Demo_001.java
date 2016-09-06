package cjy.reflect;

class Demo_001 {
    public static void main(String[] args) {
        Class<?> demo1 = null;
        try {
            demo1 = Class.forName("cjy.reflect.Demo");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("类名称：" + demo1.getName());
    }
}

class Demo {
    // other codes...
}
