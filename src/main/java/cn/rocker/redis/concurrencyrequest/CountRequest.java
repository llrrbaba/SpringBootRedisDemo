package cn.rocker.redis.concurrencyrequest;

import cn.rocker.redis.conf.RedisUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author rocker
 * @version V1.0
 * @Description:    redis实现一分钟内只发送一次短信
 * @date 2018/9/13 10:13
 */
@RestController
@RequestMapping("/redis")
public class CountRequest {

    @Resource
    private StringRedisTemplate stringRedisTemplate;


    @RequestMapping("/countRequest")
    public String countRequest(String phone){
        String smsTtlKey = "SMS_TTL_" + phone;
        long incr = 1;

        long increment = stringRedisTemplate.opsForValue().increment(smsTtlKey, incr);
        //第一次请求，increment为1，可以发送短信
        if(increment == 1){
            //设置有效期一分钟
            stringRedisTemplate.expire(smsTtlKey, 60, TimeUnit.SECONDS);
            return "可以发送短信";
        }

        //一分钟之内再次请求，increment大于1，不可以发送短信
        if(increment > 1){
            return "不可以发送短信";
        }

        return null;
    }

}
