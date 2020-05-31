package com.wdx.provider.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class RabbitMQUtils {
    private static ConnectionFactory connectionFactory;

    static {
        //创建连接mq的连接工厂对象
        connectionFactory = new ConnectionFactory();
        //设置连接rabbitmq主机
        connectionFactory.setHost("127.0.0.1");
        //设置端口号
        connectionFactory.setPort(5672);
        //设置要连接哪个虚拟主机
//        connectionFactory.setVirtualHost("");
        //设置访问虚拟主机的账号、密码
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
    }

    //定义提供连接对象的方法
    public static Connection getConnection() {
        try {
            return connectionFactory.newConnection();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        return null;
    }

    //关闭通道和关闭连接工具的方法
    public static void closeConnectionAndChannel(Channel channel, Connection conn) {
        try {
            if (channel != null)
                channel.close();
            if (conn != null)
                conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
