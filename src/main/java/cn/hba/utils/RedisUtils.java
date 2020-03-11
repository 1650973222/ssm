package cn.hba.utils;

import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author wjq
 * 2020/2/19
 */
public class RedisUtils {

    private static JedisPool jedisPool;
    static {
        JedisPoolConfig jedisPoolConfig=new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(5);
        jedisPoolConfig.setMaxIdle(1);
        jedisPool=new JedisPool(jedisPoolConfig,"192.168.159.132",6379);
    }
    public static Jedis createRedis(){
        Jedis jedis = jedisPool.getResource();
        jedis.auth("123456");
        return jedis;
    }
    public static void closeRedis(Jedis jedis){
        jedis.close();
    }
}

