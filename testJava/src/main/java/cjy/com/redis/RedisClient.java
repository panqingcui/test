package cjy.com.redis;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

public class RedisClient {
    public static void main(String[] args) throws IOException {
        // int a = 0;
        // while (a < 1000) {
        // List<JedisShardInfo> shards = Arrays.asList(new JedisShardInfo("192.168.2.41", 6379));
        // ShardedJedisPool pool = new ShardedJedisPool(new JedisPoolConfig(), shards);
        // a++;
        // // Jedis jedis = new Jedis("192.168.2.107", 6379);
        // ShardedJedis jedis = pool.getResource();
        // User user = new User("张三");
        // Address ress = new Address();
        // ress.setId("address_id");
        // ress.setName("address");
        // Map<String, Address> userMap = new HashMap<String, Address>();
        // userMap.put(user.getName(), ress);
        // user.setMap(userMap);
        // ObjectMapper mapper = new ObjectMapper();
        // String json = null;
        // try {
        // json = mapper.writeValueAsString(user);
        // } catch (JsonGenerationException e) {
        // e.printStackTrace();
        // } catch (JsonMappingException e) {
        // e.printStackTrace();
        // } catch (IOException e) {
        // e.printStackTrace();
        // }
        // System.out.println(json);
        // Map<String, String> map = new HashMap<String, String>();
        // map.put("1", json);
        // map.put("2", json);
        // jedis.hmset("json", map);
        // Map mjson = jedis.hgetAll("json");
        // Map<String, User> uMap = new HashMap<String, User>();
        // Iterator it = mjson.keySet().iterator();
        // while (it.hasNext()) {
        // String key = (String) it.next();
        // String value = (String) mjson.get(key);
        // try {
        // User usr = mapper.readValue(value, User.class);
        // uMap.put(key, usr);
        // } catch (JsonParseException e) {
        // e.printStackTrace();
        // } catch (JsonMappingException e) {
        // e.printStackTrace();
        // } catch (IOException e) {
        // e.printStackTrace();
        // }
        // }
        // System.out.println("*************************uMap*************************");
        // Iterator i = it = uMap.keySet().iterator();
        // while (i.hasNext()) {
        // String key = (String) i.next();
        // User use = uMap.get(key);
        // System.out.println(use.getName());
        // System.out.println(use.getAge());
        // System.out.println(use.getMap());
        // }
        // pool.returnResource(jedis);
        // }
        List<JedisShardInfo> shards = Arrays.asList(new JedisShardInfo("192.168.2.107", 6379));
        ShardedJedisPool pool = new ShardedJedisPool(new JedisPoolConfig(), shards);
        User user = new User();
        user.setAge(2);
        user.setName("zhang");
        ShardedJedis jedis = pool.getResource();
        jedis.set("test".getBytes(), KryoMapper.toKryo(user));
    }
}
