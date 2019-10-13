import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.Test;
import redis.clients.jedis.Jedis;

public class Main {
    @Test
    public void test() {
        Jedis jedis = new Jedis("localhost", 6379); // 1.获取连接
        jedis.set("name", "sakura1027"); // 2.操作
        jedis.close(); // 3.关闭连接
    }

    @Test
    public void test2() {
        Jedis jedis = new Jedis(); // 默认为localhost 6379
        jedis.set("name", "sakura");
        jedis.close();
    }

    @Test
    public void test3() {
        Jedis jedis = new Jedis();
        jedis.setex("name", 20, "1027");
        jedis.close();
    }

    @Test
    public void test4() {
        Jedis jedis = new Jedis();
        jedis.hset("name", "huge", "18");
        jedis.hset("name", "liuyifei", "17");
        jedis.hset("name", "wangyuyan", "16");
        String name = jedis.hget("name", "huge");
        System.out.println(name);
        Map<String, String> names = jedis.hgetAll("name");
        for (String key : names.keySet()) {
            System.out.println(key + ": " + names.get(key));
        }
        jedis.close();
    }

    @Test
    public void test5() {
        Jedis jedis = new Jedis();
        jedis.lpush("name", "a", "b", "c");
        jedis.rpush("name", "1", "2", "3");
        List<String> res = jedis.lrange("name", 0, -1);
        System.out.println(res);
        System.out.println(jedis.lpop("name"));
        jedis.close();
    }

    @Test
    public void test6() {
        Jedis jedis = new Jedis();
        jedis.sadd("name", "java", "python", "c++");
        Set<String> res = jedis.smembers("name");
        System.out.println(res);
        jedis.close();
    }

    @Test
    public void test7() {
        Jedis jedis = new Jedis();
        jedis.zadd("name", 1, "java");
        jedis.zadd("name", 2, "python");
        jedis.zadd("name", 3, "c++");
        Set<String> res = jedis.zrange("name", 0, -1);
        System.out.println(res);
        jedis.close();
    }

}
