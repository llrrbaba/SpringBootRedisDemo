package cn.rocker.redis.test;

import cn.rocker.redis.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author rocker
 * @version V1.0
 * @Description:    SpringBoot整合RedisUtils入门
 * @date 2018/6/4 10:49
 */
@RestController
@RequestMapping("redis")
public class RedisUtilsOperationTest {

    @Autowired
    private RedisUtils redisUtils;


    @RequestMapping("testUtils")
    public String testUtils(){
        redisUtils.set("message2","222hello redis utils ,i am new here");
        return redisUtils.get("message2").toString();
    }
}
