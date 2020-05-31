package com.wdx.provider.rabbitmq.direct;

import com.rabbitmq.client.*;
import com.wdx.provider.rabbitmq.RabbitMQUtils;

import java.io.IOException;

public class Consumer1 {
    public static void main(String[] args) throws IOException {
        //通过工具类获取连接对象
        Connection connection = RabbitMQUtils.getConnection();
        //获取通道对象
        Channel channel = connection.createChannel();
        //通道绑定交换机
        //参数1：交换机名称
        //参数2：交换机类型 direct 路由模式
        channel.exchangeDeclare("jhj-direct", "direct");
        //临时队列
        String queue = channel.queueDeclare().getQueue();
        //基于route key绑定交换机和队列
        channel.queueBind(queue,"jhj-direct","error");
        //消费消息
        channel.basicConsume(queue, true, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者-1:" + new String(body));
            }
        });
        //关闭资源
//        RabbitMQUtils.closeConnectionAndChannel(channel, connection);
    }
}
