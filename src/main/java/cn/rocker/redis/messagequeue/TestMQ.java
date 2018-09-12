package cn.rocker.redis.messagequeue;

/**
 * @author rocker
 * @version V1.0
 * @Description:    测试生产者|消费者
 * @date 2018/8/27 21:25
 */
public class TestMQ {

    public static void main(String[] args) throws Exception {

        // 启动一个生产者线程，模拟任务的产生
        new Thread(new Producer()).start();

        Thread.sleep(15000);

        //启动一个线程者线程，模拟任务的处理
        new Thread(new Consumer()).start();

        //主线程休眠
        Thread.sleep(Long.MAX_VALUE);
    }
}
