package test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class Test1 {
    public static void main(String[] args) {
        // System.out.println(getAge());
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("1", 1);
        map.put("2", 2);
        map.put("3", 3);
        map.put("3", 3);
        Iterator itor = map.entrySet().iterator();
        while (itor.hasNext()) {
            Entry entry = (Entry) itor.next();
            System.out.println("key=" + entry.getKey().toString());
            System.out.println("values=" + entry.getValue().toString());
        }
    }

    public static int getAge() {
        int a = 0;
        while (true) {
            a++;
            if (a > 5) {
                return a;
            }
        }
    }
}
