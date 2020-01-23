package com.order;

import com.item.*;
import com.order.dto.ItemAdditionalParametrsDto;
import com.order.repository.OrderItemRepository;
import com.order.repository.OrderRepository;
import com.order.types.OrderStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Service
public class Orders {
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    public Orders(OrderRepository orderRepository, OrderItemRepository orderItemRepository) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
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
        Order order = orderRepository.findById(orderId).get();
        String getItemUrl = "http://localhost:8081/api/warehouse/items/" + itemDto.getId();
        Item item = new RestTemplate().getForObject(getItemUrl, Item.class);

        orderItem.setItemId(item);
        orderItem.setOrderId(order);
        orderItem.setAmount(itemDto.getAmount());

        orderItemRepository.save(orderItem);
    }

    public void changeOrderStatus(Integer orderId, OrderStatus status) {
        Order order = orderRepository.findById(orderId).get();
        order.setStatus(status);
        orderRepository.save(order);
    }
}
