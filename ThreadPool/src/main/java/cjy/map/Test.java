package cjy.map;

import java.util.HashMap;
import java.util.Map;

public class Test {
    private void test() {
        // User user = new User("刘邦", 20);
        // Map<String, Map<String, User>> mapList = new HashMap<>();
        // Map<String, User> UserMap = new HashMap<String, User>();
        // UserMap.put("1", user);
        // mapList.put("1", UserMap);
        // Vector v = new Vector<>();
        // v.add(1);
        // user.setVector(v);
        // System.out.println(mapList.get("1").get("1"));
        // User u = null;
        // u = mapList.get("1").get("1");
        // Vector c = new Vector<>();
        // c.add(2);
        // c.add(3);
        // u.setVector(c);
        // System.out.println(u);
        // System.out.println(mapList);
        Map<String, String> map = new HashMap<>();
        map.put("1", "2");
        System.out.println(map.containsKey("1"));
        // FamailyUser fuser = new FamailyUser();
        // fuser.setFname("f1");
        // fuser.setUserMap(UserMap);
        // Map<String, User> map1 = fuser.getUserMap();
        // User u1 = map1.get("1");
        // u1.setUserName("test");
        // System.out.println(map1);
        // System.out.println(fuser);
    }

    public static void main(String[] args) {
        new Test().test();
    }
}
