package cjy.com.map;

import java.util.HashMap;
import java.util.Map;

public class TestMapValues {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<String, String>();
        map = null;
        System.out.print(map.values());
    }
}
