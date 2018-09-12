package cn.rocker.redis.test;

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
 * @Description:    SpringBoot整合Redis入门
 * @date 2018/6/4 10:49
 */
@RestController
@RequestMapping("/redis")
public class RedisTempOperationTest {

    /**
     * 这里能直接注入{@link StringRedisTemplate}是因为
     * {@link RedisProperties}帮我们读取了application.properties中的spring.redis的配置信息
     * 然后，{@link RedisAutoConfiguration}帮我们创建连接工厂{@link RedisConnectionFactory}
     * 并且帮我们配置模板{@link RedisTemplate}{@link StringRedisTemplate}
     */
    @Autowired
    private StringRedisTemplate template;


    @RequestMapping("/test")
    public String test(){
        template.opsForValue().set("message1","111hello redis, i am new here");
        return template.opsForValue().get("message1");
    }

}
