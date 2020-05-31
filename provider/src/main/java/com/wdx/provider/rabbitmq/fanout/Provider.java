package com.wdx.provider.rabbitmq.fanout;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.MessageProperties;
import com.wdx.provider.rabbitmq.RabbitMQUtils;

import java.io.IOException;

public class Provider {
    public static void main(String[] args) throws IOException {
        //通过工具类获取连接对象
        Connection connection = RabbitMQUtils.getConnection();
        //获取通道对象
        Channel channel = connection.createChannel();
        //通道绑定交换机
        //参数1：交换机名称
        //参数2：交换机类型 fanout广播类型
        channel.exchangeDeclare("jhj", "fanout");
        //生产消息
        channel.basicPublish("jhj", "", null, "fanout type message".getBytes());
        //关闭资源
        RabbitMQUtils.closeConnectionAndChannel(channel, connection);
    }
}
