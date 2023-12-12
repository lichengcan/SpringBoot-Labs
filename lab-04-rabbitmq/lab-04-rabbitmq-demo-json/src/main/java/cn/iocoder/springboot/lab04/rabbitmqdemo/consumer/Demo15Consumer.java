package cn.iocoder.springboot.lab04.rabbitmqdemo.consumer;

import cn.iocoder.springboot.lab04.rabbitmqdemo.message.Demo15Message;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RabbitListener(queues = Demo15Message.QUEUE)
public class Demo15Consumer {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @RabbitHandler(isDefault = true)
    public void onMessage(Message message, Channel channel) throws IOException {
        try {

            //手动确认消费，basicAck两个参数：
            //deliveryTag：表示消息的传递标签，用于唯一标识一个消息。
            //multiple：表示是否批量确认，如果设置为 true，则表示确认所有小于等于 deliveryTag 的消息；如果设置为 false，则只确认当前 deliveryTag 对应的消息。
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            logger.info("[onMessage][线程编号:{} 消息内容：{}]", Thread.currentThread().getId(),
                    new String(message.getBody()));
        } catch (IOException e) {
            //发生异常，拒绝当前信息，使其重新进入队列
            channel.basicReject(message.getMessageProperties().getDeliveryTag(), true);
            logger.error("[onMessage][消息消费失败，消息将重新进入队列]");
        }catch (Exception e) {
            // 其他异常处理
            logger.error("[onMessage][处理消息发生异常]", e);
        }
    }

}
