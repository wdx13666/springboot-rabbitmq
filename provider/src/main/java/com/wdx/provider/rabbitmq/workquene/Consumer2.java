package com.wdx.provider.rabbitmq.workquene;

import com.rabbitmq.client.*;
import com.wdx.provider.rabbitmq.RabbitMQUtils;

import java.io.IOException;

public class Consumer2 {
    public static void main(String[] args) throws IOException {
        //获取连接对象
        Connection connection = RabbitMQUtils.getConnection();
        //获取通道对象
        Channel channel = connection.createChannel();
        //通过通道声明队列
        channel.queueDeclare("work", true, false, false, null);
        //每一次只能消费一个消息
        channel.basicQos(1);
        //参数1：队列名称
        //参数2：消息自动确认 true消费者自动向rabbitmq确认消息消费
        channel.basicConsume("work", false, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("消费者-2:" + new String(body));
                //手动确认
                //参数1：确认队列中哪个具体消息 手动确认消息标识  参数2：是否开启多个消息同事确定 false每次确认一个
                channel.basicAck(envelope.getDeliveryTag(),false);
            }
        });
        //关闭资源
//        RabbitMQUtils.closeConnectionAndChannel(channel, connection);
    }
}
