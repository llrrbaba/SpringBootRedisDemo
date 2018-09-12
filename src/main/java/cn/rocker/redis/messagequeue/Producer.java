package cn.rocker.redis.messagequeue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;
import java.util.Random;
import java.util.UUID;

/**
 * @author rocker
 * @version V1.0
 * @Description: 模拟一个生产者
 * @date 2018/8/27 21:10
 */
@Component
public class Producer implements Runnable {

    Jedis jedis = new Jedis("192.168.112.128",6379);

    @Override
    public void run() {
        Random random = new Random();
        while(true){
            try{
                Thread.sleep(random.nextInt(600) + 600);
                // 模拟生成一个任务
                UUID taskid = UUID.randomUUID();
                //将任务插入任务队列：task-queue
                jedis.lpush("task-queue", taskid.toString());
                System.out.println("插入了一个新的任务： " + taskid);

            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}
