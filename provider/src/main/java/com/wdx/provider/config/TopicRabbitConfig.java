package com.wdx.provider.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Topic Exchange 主题交换机
 */
@Configuration
public class TopicRabbitConfig {

    //绑定键
    private final static String man = "topic.man";
    private final static String women = "topic.women";

    @Bean
    public Queue man(){
        return new Queue(man);
    }

    @Bean
    public Queue women(){
        return new Queue(women);
    }

    @Bean
    TopicExchange exchange(){
        return  new TopicExchange("topicExchange");
    }

    //将man和topicExchange绑定,而且绑定的键值为topic.man
    //这样只要是消息携带的路由键是topic.man,才会分发到该队列
    @Bean
    Binding bindingExchangeMessage() {
        return BindingBuilder.bind(man()).to(exchange()).with(man);
    }

    //将women和topicExchange绑定,而且绑定的键值为用上通配路由键规则topic.#
    // 这样只要是消息携带的路由键是以topic.开头,都会分发到该队列
    @Bean
    Binding bindingExchangeMessage2() {
        return BindingBuilder.bind(women()).to(exchange()).with("topic.#");
    }


}
