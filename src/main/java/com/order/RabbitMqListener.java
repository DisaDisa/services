package com.order;

import com.google.gson.Gson;
import com.item.Items;
import com.order.types.OrderStatus;
import com.payment.dto.OrderStatusUpdateDto;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@EnableRabbit
@Component
public class RabbitMqListener {
    @Autowired
    private Orders orderController;

    private static Logger logger = Logger.getLogger(RabbitMqListener.class.getName());

    @RabbitListener(queues = "order-status-update")
    public void orderStatusUpdateQueue(String message){
        Gson gson = new Gson();
        OrderStatusUpdateDto orderStatusUpdateDto = gson.fromJson(message, OrderStatusUpdateDto.class);
        logger.info("accepted on order : " + orderStatusUpdateDto);
        orderController.changeOrderStatus(orderStatusUpdateDto.getOrderId(), orderStatusUpdateDto.getNewStatus());
    }

}
