package cjy.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.JarURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.jar.Attributes;

public class FileTest {
    private static final boolean ESCAPE_SPACES = !"false".equals(System.getProperty("PluginLoader.ESCAPE_SPACES"));

    public static void main(String[] args) throws Exception {
        // File file = new File(System.getProperty("use.home"), ".hq");
        // File files = new File(file, "1.txt");
        // System.out.println(System.getProperty("use.home"));
        // System.out.println(file);
        // System.out.println(files);
        // File file = new File("D:/");
        // for (File files : file.listFiles()) {
        // System.out.println(files.getName());
        // }
        // String name = new File("D//a.txt").getName();
        // System.out.println(name);
        // new FileTest().test();
        File file = new File("C:/Users/Administrator/Desktop/课件/ibatis学习笔记.docx");
        System.out.println(file.getName());
        URL[] u = new URL[0];
        System.out.println(u);
        URL jarUrl = toJarURL("antsoldier-activemq-plugin-3.0.0-SNAPSHOT.jar");
        // System.out.println(jarUrl);
        // URL u1 = new URL("jar",);
        // try{ String jarname = getPluginMainClass(jarUrl);
        // System.out.println(jarname);
        // }
    }

    private static URL toJarURL(String file) throws MalformedURLException {
        return new URL("jar", "", toFileURL(file) + "!/");
    }

    private static String toFileURL(String file) {
        System.out.println("ESCAPE_SPACES:" + ESCAPE_SPACES);
        if (ESCAPE_SPACES) {
            file = replace(file, " ", "%20");
        }
        return "file:" + file;
    }

    public static String replace(String source, String find, String replace) {
        if (source == null || find == null || replace == null) {
            return source;
        }
        int sourceLen = source.length();
        int findLen = find.length();
        if (sourceLen == 0 || findLen == 0) {
            return source;
        }
        StringBuffer buffer = new StringBuffer();
        int idx, fromIndex;
        for (fromIndex = 0; (idx = source.indexOf(find, fromIndex)) != -1; fromIndex = idx + findLen) {
            buffer.append(source.substring(fromIndex, idx));
            buffer.append(replace);
        }
        if (fromIndex == 0) {
            return source;
        }
        buffer.append(source.substring(fromIndex));
        return buffer.toString();
    }

    public static String getPluginMainClass(URL url) throws Exception {
        JarURLConnection jarConn = (JarURLConnection) url.openConnection();
        Attributes attrs = jarConn.getMainAttributes();
        String pluginName = attrs.getValue(Attributes.Name.MAIN_CLASS);
        return pluginName;
    }

    private void test() {
        // ClassLoader loader = this.getClass().getClassLoader();
        // InputStream is = null;
        // loader.getResourceAsStream("");
        byte[] pluginStub = new byte[1024];
        ClassLoader loader = this.getClass().getClassLoader();
        File file = new File("D:/data/1.txt");
        int len;
        InputStream is = null;
        try {
            // is = loader.getResourceAsStream("D:/data/log/server.log");
            // is= file.get
            is = new FileInputStream(file);
            is.read(pluginStub);
            String str = new String(pluginStub);
            System.out.println(str);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {}
            }
        }
    }
}
