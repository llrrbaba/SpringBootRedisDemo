package cn.rocker.redis.pubsub;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;

/**
 * @author rocker
 * @version V1.0
 * @Description:    redis订阅者
 * @date 2018/9/12 21:23
 */
public class RedisEnterSubscriber implements MessageListener {

    private static Logger logger = LoggerFactory.getLogger(RedisEnterSubscriber.class);

    @Override
    public void onMessage(Message message, byte[] pattern) {
        logger.info("收到{}频道的进场消息：{}",message);

        //处理业务
        System.out.println(message);
    }
}
