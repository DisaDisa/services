package com.order;

import com.google.gson.Gson;
import com.item.*;
import com.item.dto.ItemUpdateDto;
import com.order.dto.ItemAdditionalParametrsDto;
import com.order.dto.OrderDto;
import com.order.repository.OrderItemRepository;
import com.order.repository.OrderRepository;
import com.order.types.OrderStatus;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class Orders {
    @Autowired
    AmqpTemplate template;


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

    public void addOrder(OrderDto orderDto) {
        Order order = new Order();

        order.setStatus(orderDto.getStatus());
        order.setTotalCost(orderDto.getTotalCost());
        order.setTotalAmount(orderDto.getTotalAmount());

        orderRepository.save(order);
    }

    public void addItemToOrder(Integer orderId, ItemAdditionalParametrsDto itemDto) {
        OrderItem orderItem = new OrderItem();

        orderItem.setItemId(itemDto.getId());
        orderItem.setOrderId(orderId);
        orderItem.setAmount(itemDto.getAmount());

        orderItemRepository.save(orderItem);
        ItemUpdateDto itemUpdateDto = new ItemUpdateDto();
        itemUpdateDto.setAmount(itemDto.getAmount());
        itemUpdateDto.setId(itemDto.getId());
        Gson gson = new Gson();
        String json = gson.toJson(itemUpdateDto);
        template.convertAndSend("item-update", json);
        /*
        String urlLine = "http://localhost:8081/api/warehouse/items/" + itemDto.getId().toString();
        Item item = new RestTemplate().getForObject(urlLine, Item.class);

        Order order = orderRepository.findById(orderId).get();
        */

    }

    public void changeOrderStatus(Integer orderId, OrderStatus status) {
        Order order = orderRepository.findById(orderId).get();
        order.setStatus(status);
        orderRepository.save(order);
    }
}
