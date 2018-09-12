package cn.rocker.redis.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.Serializable;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Component
public class RedisUtils {
	@SuppressWarnings("rawtypes")  
    @Autowired
    private RedisTemplate redisTemplate;


	public void convertAndSend(String channel, String message){
	    redisTemplate.convertAndSend(channel, message);
    }

    @Autowired
    private RedisConn redisConn;

    /**
     * 连接redis服务端
     */
    public JedisPool getJedisPool() {
        // 连接redis服务端
        JedisPool jedisPool = new JedisPool(new JedisPoolConfig(), redisConn.getHost(), redisConn.getPort(),
                redisConn.getTimeout(), redisConn.getPassword());
        return jedisPool;
    }


    /**
     * 获取redis
     */
    public Jedis getJedis() {
        JedisPool jedisPool = getJedisPool();
        Jedis jedis = jedisPool.getResource();
        return jedis;
    }



    /** 
     * 批量删除对应的value 
     */
    public void remove(final String... keys) {  
        for (String key : keys) {  
            remove(key);  
        }  
    }  
  
    /** 
     * 批量删除key 
     */
    @SuppressWarnings("unchecked")  
    public void removePattern(final String pattern) {  
        Set<Serializable> keys = redisTemplate.keys(pattern);  
        if (keys.size() > 0)  
            redisTemplate.delete(keys);  
    }  


    /** 
     * 删除对应的value 
     */
    @SuppressWarnings("unchecked")  
    public void remove(final String key) {  
        if (exists(key)) {  
            redisTemplate.delete(key);  
        }  
    }  
  
    /** 
     * 判断缓存中是否有对应的value 
     */
    @SuppressWarnings("unchecked")  
    public boolean exists(final String key) {  
        return redisTemplate.hasKey(key);  
    }  


    /** 
     * 读取缓存 
     */
    @SuppressWarnings("unchecked")  
    public Object get(final String key) {  
        Object result = null;  
        ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
        result = operations.get(key);  
        return result;  
    }  
  
    /** 
     * 写入缓存 
     *  
     * @param key 
     * @param value 
     * @return 
     */  
    @SuppressWarnings("unchecked")  
    public boolean set(final String key, Object value) {  
        boolean result = false;  
        try {  
            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            operations.set(key, value);  
            result = true;  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return result;  
    }  
  
    /** 
     * 写入缓存 
     */
    @SuppressWarnings("unchecked")  
    public boolean set(final String key, Object value, long expireTime) {
        boolean result = false;  
        try {  
            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            operations.set(key, value);  
            redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);  
            result = true;  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return result;  
    } 
}
