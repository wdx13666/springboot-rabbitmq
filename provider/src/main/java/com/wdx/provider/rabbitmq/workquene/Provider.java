package com.wdx.provider.rabbitmq.workquene;

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

        //通道绑定消息队列
        //参数1：队列名称 队列不存在自动穿件
        //参数2：定义队列特性是否要持久化 true持久化队列 false不持久化
        //参数3：exclusive 是否独占队列 true独占队列 false 不独占
        //参数4：autoDelete 是否在消费完成后自动删除队列 true 自动删除  false 不自动删除
        //参数5：额外附加参数
        channel.queueDeclare("work", true, false, false, null);
        for (int i = 1; i <= 100; i++) {
            //生产消息
            //参数1：交换机名称
            //参数2：队列名称
            //参数3：传递消息额外设置
            //参数4：消息的具体内容
            channel.basicPublish("", "work", MessageProperties.PERSISTENT_TEXT_PLAIN, ("工作队列" + i).getBytes());
        }
        //关闭资源
        RabbitMQUtils.closeConnectionAndChannel(channel, connection);
    }
}
