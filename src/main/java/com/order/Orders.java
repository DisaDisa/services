package com.order;

import com.item.dto.ItemAdditionalParametrsDto;
import com.item.repository.ItemRepository;
import com.order.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class Orders {
    private final OrderRepository orderRepository;

    public Orders(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> getOrders() {
        ArrayList<Order> orders = new ArrayList<>();
        Iterable<Order> allOrders = orderRepository.findAll();
        for (Order curOrder : allOrders) {
            orders.add(curOrder);
        }
        return orders;
    }

    public Order getOrderById(Integer orderId) {
        Optional<Order> orders = orderRepository.findById(orderId);
        return orders.get();
    }

    public void addItemToOrder(Integer orderId, ItemAdditionalParametrsDto itemDto) {
        OrderItem orderItem = new OrderItem();
        /*
        orderItem.setItemId();
        order
         */
    }

    public void changeOrderStatus(Integer orderId, Integer status) {
        Order order = orderRepository.findById(orderId).get();
        order.setStatus(status);
        orderRepository.save(order);
    }
}
