import org.junit.jupiter.api.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class Main2 {
    @Test
    public void test() {
        // 1.创建Jedis连接池对象
        JedisPool jedisPool = new JedisPool();
        // 2.获取连接
        Jedis jedis = jedisPool.getResource();
        // 3.操作
        jedis.set("hello", "world");
        jedis.close();
    }

    @Test
    public void test2() {
        JedisPoolConfig config = new JedisPoolConfig(); // 创建配置对象
        config.setMaxTotal(50);
        config.setMaxIdle(10);

        JedisPool jedisPool = new JedisPool(config, "localhost", 6379);
        Jedis jedis = jedisPool.getResource();
        jedis.set("hello", "sakura");
        jedis.close();
    }

    @Test
    public void test3() {
        Jedis jedis = JedisPoolUtils.getJedis();
        jedis.set("hello", "google");
        System.out.println(jedis.get("hello"));
        jedis.close();
    }

}
