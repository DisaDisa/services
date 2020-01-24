package com.item;

import com.item.dto.ItemUpdateDto;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@EnableRabbit
@Component
public class RabbitMqListener {
    @Autowired
    private Items itemsController;


    @RabbitListener(queues = "item-update")
    public void itemUpdateHandler(@Payload ItemUpdateDto itemUpdate) {
        itemsController.updateItem(itemUpdate.getId(), itemUpdate.getAmount());
    }

}