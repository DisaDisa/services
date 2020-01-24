package com.order;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@EnableRabbit
@Component
public class RabbitMqListener {
    Logger logger = Logger.getLogger(RabbitMqListener.class.getName());

    @RabbitListener(queues = "order-status-update")
    public void orderStatusUpdateQueue(String message){
        logger.info("accepted on order : " + message);
    }

}
