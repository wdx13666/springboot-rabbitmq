package com.wdx.consumer.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DirectRabbitConfig {

    //队列起名:  directQueue
    @Bean
    public Queue DirectQueue(){
        return new Queue("directQueue",true);
    }

    //Direct交换机 起名：directExchange
    @Bean
    public DirectExchange directExchange(){
        return new DirectExchange("directExchange");
    }

    //绑定  将队列和交换机绑定, 并设置用于匹配键：directRouing
    @Bean
    public Binding bindingDirect(){
        return BindingBuilder.bind(DirectQueue()).to(directExchange()).with("directRouing");
    }
}
