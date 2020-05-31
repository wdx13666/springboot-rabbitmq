package com.wdx.provider.rabbitmq.direct;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
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
        //参数2：交换机类型 direct 路由模式
        channel.exchangeDeclare("jhj-direct", "direct");
        //生产消息
        String routingkey = "error";
        channel.basicPublish("jhj-direct", routingkey, null, ("direct 基于 routingkey:" + routingkey + "发送的消息").getBytes());
        //关闭资源
        RabbitMQUtils.closeConnectionAndChannel(channel, connection);
    }
}
