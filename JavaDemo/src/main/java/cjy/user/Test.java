package cjy.user;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Test {
    public static void main(String[] args) throws Exception {
        Singleton d1 = Singleton.INSTANCE;
        d1.setName("a fucker.");
        System.out.println(d1);
        FileOutputStream fos = new FileOutputStream("out.data");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(d1);
        fos.close();
        oos.close();
        FileInputStream fis = new FileInputStream("out.data");
        ObjectInputStream ois = new ObjectInputStream(fis);
        Object o = ois.readObject();
        fis.close();
        ois.close();
        Singleton d2 = (Singleton) o;
        System.out.println(d2);
        System.out.println(d1 == d2);
    }
}

enum Singleton implements Serializable {
    INSTANCE;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "[" + name + "]";
    }
}
