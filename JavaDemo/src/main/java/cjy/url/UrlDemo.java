package cjy.url;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.JarURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;
import java.util.jar.Attributes;

import cjy.reflect.LicenseManager;

public class UrlDemo {
    public static void main(String[] args) throws ClassNotFoundException, SecurityException, NoSuchMethodException,
            IllegalArgumentException, IllegalAccessException, InvocationTargetException {
        new UrlDemo().logPluginManifes("D:/antsoldier-activemq-plugin-3.0.0-SNAPSHOT.jar");
        // Map<Integer, String> map = new HashMap<Integer, String>();
        // for (int i = 0; i < 2000; i++) {
        // map.put(i, i * 10 + "");
        // }
        // // 第一种:通过Map.keySet()遍历key和value
        // // 这里有一个二次取值的过程,所以并不推荐
        // for (Integer key : map.keySet()) {
        // System.out.println("key = " + key + " and value = " + map.get(key));
        // }
        // Iterator it = map.keySet().iterator();
        // while (it.hasNext()) {
        // // int key = (Integer) it.next();
        // // System.out.println("key =" + key + "  and value = " + map.get(key));
        // // Map.Entry entry = (Map.Entry) it.next();
        // // System.out.println(entry.getKey() + "" + entry.getValue());
        // }
        Class<?> c = Class.forName("cjy.reflect.LicenseManager");
        Method getInstance = c.getMethod("instance", null);
        LicenseManager mgr = (LicenseManager) getInstance.invoke(null, null);
    }

    public void logPluginManifes(String jarname) {
        ClassLoader loader = this.getClass().getClassLoader();
        System.out.println(this.getClass().getClassLoader());
        Thread.currentThread().getContextClassLoader();
        URL url;
        try {
            url = new URL("jar", "", "file:" + jarname + "!/");
            System.out.println(url);
            JarURLConnection jarUrlConnection = (JarURLConnection) url.openConnection();
            Map attributs = jarUrlConnection.getManifest().getMainAttributes();
            Attributes att = jarUrlConnection.getManifest().getMainAttributes();
            String attname = att.getValue(Attributes.Name.MANIFEST_VERSION);
            System.out.println(attname);
            if (!attributs.isEmpty()) {
                StringBuffer sb = new StringBuffer("");
                Iterator it = attributs.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry key = (Map.Entry) it.next();
                    System.out.println(key.getKey() + ":" + key.getValue());
                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {}
    }
}
