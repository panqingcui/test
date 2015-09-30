package cjy.map;

import java.util.HashMap;
import java.util.Map;

public class Test {
    private void test() {
        User user = new User("刘邦", 20);
        Map<String, User> UserMap = new HashMap<String, User>();
        UserMap.put("1", user);
        FamailyUser fuser = new FamailyUser();
        fuser.setFname("f1");
        fuser.setUserMap(UserMap);
        Map<String, User> map1 = fuser.getUserMap();
        User u1 = map1.get("1");
        u1.setUserName("test");
        System.out.println(map1);
        System.out.println(fuser);
    }

    public static void main(String[] args) {
        new Test().test();
    }
}
