package com.wdx.consumer.config;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RabbitListener(queues = "directQueue") //监听的队列名称 directQueue
public class DirectReceiver {

    @RabbitHandler
    public void process(Map message){
        System.out.println("DirectReceiver消费者收到消息  : " + message.toString());
    }
}
