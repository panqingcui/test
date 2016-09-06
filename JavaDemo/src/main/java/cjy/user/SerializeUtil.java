package cjy.user;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SerializeUtil {
    /**
     * 序列化对象
     * 
     * @throws IOException
     */
    public static byte[] serializeObject(Object object) throws IOException {
        ByteArrayOutputStream saos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(saos);
        oos.writeObject(object);
        oos.flush();
        return saos.toByteArray();
    }

    /**
     * 反序列化对象
     * 
     * @throws IOException
     * 
     * @throws ClassNotFoundException
     */
    public static Object deserializeObject(byte[] buf) throws IOException, ClassNotFoundException {
        Object object = null;
        ByteArrayInputStream sais = new ByteArrayInputStream(buf);
        ObjectInputStream ois = new ObjectInputStream(sais);
        // object = (MatchMovie) ois.readObject();
        return object;
    }
}
