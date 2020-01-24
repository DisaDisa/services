package com.item;

import com.item.dto.ItemUpdateDto;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@EnableRabbit
@Component
public class RabbitMqListener {
    @Autowired
    private Items itemsController;

    private static Logger log = Logger.getLogger(ItemController.class.getName());


    @RabbitListener(queues = "item-update")
    public void itemUpdateHandler(@Payload ItemUpdateDto itemUpdate) {
        log.info("itemUpdateHandler");
        itemsController.updateItem(itemUpdate.getId(), itemUpdate.getAmount());
    }

}