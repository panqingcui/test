package cjy.system;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Property {
    public static void main(String[] args) throws IOException {
        System.setProperty("open", "张三");
        System.out.println(System.getProperty("open"));
        // File file = new File("fruit.properties");
        // properties.load(new FileInputStream(file));
        InputStream in = ClassLoader.getSystemResourceAsStream("fruit.properties");
        Properties properties = new Properties();
        properties.load(in);
        System.out.println(properties.getProperty("apple"));
    }
}
