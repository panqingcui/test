package cjy.serialize;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class SerialDBTest {
    public static void main(String[] args) throws IOException {
        File file = new File("D:/antrol/1");
        FileInputStream in = new FileInputStream(file);
        // SerializeUtil s = new SerializeUtil();
        byte[] b = new byte[6000];
        in.read(b);
        System.out.println(b[1]);
        // System.out.print(((GMap) s.deserializeObject(b)).getAutoLayout());
    }
}
