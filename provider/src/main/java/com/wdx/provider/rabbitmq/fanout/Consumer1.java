package com.wdx.provider.rabbitmq.fanout;

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
        //参数2：交换机类型 fanout广播类型
        channel.exchangeDeclare("jhj", "fanout");
        //临时队列
        String queue = channel.queueDeclare().getQueue();
        //绑定交换机和队列
        channel.queueBind(queue,"jhj","");
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
