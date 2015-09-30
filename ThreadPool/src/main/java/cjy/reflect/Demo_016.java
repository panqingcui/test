package cjy.reflect;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class Demo_016 {
    public static void main(String[] a) throws FileNotFoundException, IOException {
        Properties pro = init.getProperties();
        fruit f = Factory.getInstance(pro.getProperty("apple"));
        if (f != null) {
            f.eat();
        }
    }
}

class init {
    public static Properties getProperties() throws FileNotFoundException, IOException {
        Properties properties = new Properties();
        File f = new File("fruit.properties");
        if (f.exists()) {
            properties.load(new FileInputStream(f));
        } else {
            properties.setProperty("apple", "cjy.reflect.Apple");
            properties.setProperty("orange", "cjy.reflect.Orange");
            properties.store(new FileOutputStream(f), "fruitname");
        }
        return properties;
    }
}
