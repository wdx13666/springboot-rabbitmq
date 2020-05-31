package com.wdx.provider.rabbitmq.topic;

import com.rabbitmq.client.*;
import com.wdx.provider.rabbitmq.RabbitMQUtils;

import java.io.IOException;

public class Consumer2 {
    public static void main(String[] args) throws IOException {
        //通过工具类获取连接对象
        Connection connection = RabbitMQUtils.getConnection();
        //获取通道对象
        Channel channel = connection.createChannel();
        //通道绑定交换机
        //参数1：交换机名称
        //参数2：交换机类型 topic
        channel.exchangeDeclare("jhj-topic", "topic");
        //临时队列
        String queue = channel.queueDeclare().getQueue();
        //动态通配符形式绑定交换机和队列
        channel.queueBind(queue,"jhj-topic","user.#");
        //消费消息
        channel.basicConsume(queue, true, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者-2:" + new String(body));
            }
        });
        //关闭资源
//        RabbitMQUtils.closeConnectionAndChannel(channel, connection);
    }
}
