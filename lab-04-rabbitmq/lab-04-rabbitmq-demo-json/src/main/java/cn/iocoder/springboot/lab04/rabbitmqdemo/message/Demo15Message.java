package cn.iocoder.springboot.lab04.rabbitmqdemo.message;

import lombok.Data;

import java.io.Serializable;

@Data
public class Demo15Message implements Serializable {
    /**
     QUEUE 表示消息队列的名称，它是消息在 RabbitMQ 中的目标队列。消费者从该队列接收消息，并处理这些消息。
     EXCHANGE 表示交换机的名称，它是消息发布的地点。生产者将消息发送到交换机，然后交换机根据其类型和绑定规则将消息路由到相应的队列。
     ROUTING_KEY 表示路由键，它是用于匹配消息与队列之间的关联规则。交换机使用路由键来决定将消息发送到哪个队列。
     */
    public static final String QUEUE = "QUEUE_DEMO_15";

    public static final String EXCHANGE = "EXCHANGE_DEMO_15";

    public static final String ROUTING_KEY = "ROUTING_KEY_15";

    /**
     * 编号
     */
    private Integer id;
    private String content;

}
