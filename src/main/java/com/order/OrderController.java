package com.order;

import com.order.dto.ItemAdditionalParametrsDto;
import com.order.dto.OrderDto;
import com.order.types.OrderStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
public class OrderController {
    private final Orders ordersController;

    public OrderController(Orders ordersController) {
        this.ordersController = ordersController;
    }

    @GetMapping("/api/orders")
    public List<Order> getOrders() {
        return ordersController.getOrders();
    }

    @GetMapping("/api/orders/{order_id}")
    public Order getOrderById(@PathVariable Integer order_id) {
        return ordersController.getOrderById(order_id);
    }

    @PostMapping("/api/orders")
    public void addOrder(@RequestBody OrderDto orderDto) {
        ordersController.addOrder(orderDto);
    }
    @PostMapping("/api/orders/{order_id}/item")
    public void addItemToOrder(@PathVariable Integer order_id, @RequestBody ItemAdditionalParametrsDto itemDto) {
        ordersController.addItemToOrder(order_id, itemDto);
    }

    @PutMapping("/api/orders/{order_id}/status/{status}/")
    public void changeOrderStatus(@PathVariable Integer order_id, @PathVariable OrderStatus status) {
        ordersController.changeOrderStatus(order_id, status);
    }

}
