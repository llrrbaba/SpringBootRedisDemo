package cn.rocker.redis.pubsub;

import cn.rocker.redis.conf.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author rocker
 * @version V1.0
 * @Description:    redis发布者
 * @date 2018/9/12 21:31
 */
@RestController
@RequestMapping(("/redis"))
public class RedisEnterPublisher {

    @Autowired
    private RedisUtils redisUtils;

    @RequestMapping("/publisMessage")
    public String publisMessage(){
        redisUtils.convertAndSend("enter","i am coming");
        return "success";
    }
}
