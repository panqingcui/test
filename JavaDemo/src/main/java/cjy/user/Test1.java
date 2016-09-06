package cjy.user;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;

public class Test1 {
    public static void main(String[] args) {
        User u = new User("张三", 20);
        Map<Integer, User> map = new HashMap<Integer, User>();
        map.put(1, u);
        System.out.println(JSON.toJSONString(map));
        User u1 = map.get(1);
        Address ad = new Address();
        ad.setName("上海");
        Map<Integer, Address> admap = new HashMap<Integer, Address>();
        admap.put(1, ad);
        u1.setRess(admap);
        map.put(1, u1);
        System.out.println(JSON.toJSONString(map));
        u = map.get(1);
        admap = u.getRess();
        ad = admap.get(1);
        ad.setName("北京");
        System.out.println(JSON.toJSONString(map));
    }
}
