import java.io.InputStream;
import java.util.Properties;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisPoolUtils {
    private static JedisPool jedisPool;

    static {
        // 读取配置文件
        InputStream in = JedisPoolUtils.class
                .getClassLoader().getResourceAsStream("jedis.properties");
        // 创建Properties对象
        Properties pro = new Properties();
        try {
            pro.load(in);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        // 设置JedisPoolConfig
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(Integer.parseInt(pro.getProperty("maxTotal")));
        config.setMaxIdle(Integer.parseInt(pro.getProperty("maxIdle")));
        jedisPool = new JedisPool(config, pro.getProperty("host"), Integer.parseInt(pro.getProperty("port")));
    }

    public static Jedis getJedis() {
        return jedisPool.getResource();
    }
}
