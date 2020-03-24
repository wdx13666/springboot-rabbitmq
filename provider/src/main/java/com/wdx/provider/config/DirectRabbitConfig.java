package com.wdx.provider.config;

import com.rabbitmq.client.AMQP;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * direct exchange(直连型交换机)
 */
@Configuration
public class DirectRabbitConfig {

//  队列起名: directQueue
    @Bean
    public Queue directQueue(){
        return new Queue("directQueue",true); //true 是否持久
    }

    //Direct交换机 起名：directExchange
    @Bean
    DirectExchange directExchange(){
        return new DirectExchange("directExchange");
    }

    //绑定  将队列和交换机绑定, 并设置用于匹配键：DirectRouting
   @Bean
    Binding bindingDirect(){
        return BindingBuilder.bind(directQueue()).to(directExchange()).with("directRouting");
    }
}
