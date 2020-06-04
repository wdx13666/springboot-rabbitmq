package com.wdx.consumer.config;

import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

/** 延时队列 */
@Component
public class DeplayReceiver {

  @RabbitListener(
      bindings =
          @QueueBinding(
              value = @Queue(value = "delay.queue.demo.delay.queue"),
              exchange =
                  @Exchange(value = "delay.queue.demo.delay.exchange",delayed = "true"),
              key = "delay.queue.demo.delay.routingkey"))
  @RabbitHandler
  public void receiveD(Map message) throws IOException {

    System.out.println(message.toString());
    System.out.println(message);
  }


}
