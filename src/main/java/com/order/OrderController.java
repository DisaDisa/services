package com.order;

import com.item.ItemController;
import com.order.dto.ItemAdditionalParametrsDto;
import com.order.dto.OrderDto;
import com.order.types.OrderStatus;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

@RestController
public class OrderController {
    private final Orders ordersController;
    private static Logger log = Logger.getLogger(OrderController.class.getName());

    @Autowired
    AmqpTemplate template;

    public OrderController(Orders ordersController) {
        this.ordersController = ordersController;
    }

    @GetMapping("/api/orders")
    public List<Order> getOrders() {
        log.info("Controller getOrders");
        return ordersController.getOrders();
    }

    @GetMapping("/api/orders/{order_id}")
    public Order getOrderById(@PathVariable Integer order_id) {
        log.info("Controller getOrderById " + order_id);
        return ordersController.getOrderById(order_id);
    }

    @PostMapping("/api/orders")
    public void addOrder(@RequestBody OrderDto orderDto) {
        log.info("Controller addOrder " + orderDto);
        ordersController.addOrder(orderDto);
    }

    @PostMapping("/api/orders/{order_id}/item")
    public void addItemToOrder(@PathVariable Integer order_id, @RequestBody ItemAdditionalParametrsDto itemDto) {
        log.info("Controller addItemToOrder " + order_id + " " + itemDto);
        ordersController.addItemToOrder(order_id, itemDto);
    }

    @PutMapping("/api/orders/{order_id}/status/{status}/")
    public void changeOrderStatus(@PathVariable Integer order_id, @PathVariable OrderStatus status) {
        log.info("Controller changeOrderStatus " + order_id + " " + status);
        ordersController.changeOrderStatus(order_id, status);
    }

}
