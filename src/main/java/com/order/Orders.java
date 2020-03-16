package com.order;

import com.google.gson.Gson;
import com.item.Item;
import com.item.dto.ItemUpdateDto;
import com.netflix.appinfo.EurekaInstanceConfig;
import com.order.dto.ItemAdditionalParametrsDto;
import com.order.dto.OrderDto;
import com.order.dto.OrderItemDto;
import com.order.repository.OrderItemRepository;
import com.order.repository.OrderRepository;
import com.order.types.OrderStatus;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import javax.servlet.http.HttpServletRequest;
import java.util.*;


@Service
public class Orders {
    @Autowired
    AmqpTemplate template;

    private static Map<OrderStatus, OrderStatus> statusTransitionMap = new HashMap<>();
    static {
        statusTransitionMap.put(OrderStatus.PAID, OrderStatus.COLLECTING);
        statusTransitionMap.put(OrderStatus.FAILED, OrderStatus.COLLECTING);
        statusTransitionMap.put(OrderStatus.SHIPPING, OrderStatus.PAID);
        statusTransitionMap.put(OrderStatus.CANCELLED, OrderStatus.PAID);
        statusTransitionMap.put(OrderStatus.COMPLETE, OrderStatus.SHIPPING);
    }

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    public Orders(OrderRepository orderRepository, OrderItemRepository orderItemRepository) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
    }

    public List<OrderDto> getOrders() {
        ArrayList<OrderDto> orders = new ArrayList<>();
        Iterable<Order> allOrders = orderRepository.findAll();
        for (Order curOrder : allOrders) {
            OrderDto order = new OrderDto();
            order.setStatus(curOrder.getStatus());
            order.setTotalAmount(curOrder.getTotalAmount());
            order.setTotalCost(curOrder.getTotalCost());
            order.setId(curOrder.getId());
            orders.add(order);
        }
        return orders;
    }

    public OrderItemDto getOrderById(Integer orderId) {
        Optional<Order> orders = orderRepository.findById(orderId);
        Order order = orders.get();
        OrderItemDto orderDto = new OrderItemDto();
        orderDto.setStatus(order.getStatus());
        orderDto.setTotalCost(order.getTotalCost());
        orderDto.setTotalAmount(order.getTotalAmount());
        orderDto.setId(order.getId());

        String url = "http://localhost:8081/api/warehouse/items/";
        ArrayList<Item> items = new ArrayList<>();
        Iterable<OrderItem> allOrderItems = orderItemRepository.findAll();

        for (OrderItem curOrderItem : allOrderItems) {
            if (curOrderItem.getOrderId() == orderId) {
                int itemId = curOrderItem.getItemId();
                Item item = new RestTemplate().getForObject(url + Integer.toString(itemId), Item.class);
                items.add(item);
            }
        }
        System.out.println(items.size());
        orderDto.setItems(items);
        return orderDto;
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
        OrderStatus currentStatus = order.getStatus();

        if (statusTransitionMap.get(status) == currentStatus){
            order.setStatus(status);
            orderRepository.save(order);
        }
    }
}
