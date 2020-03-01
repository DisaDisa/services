package com.zuul;

import com.item.Item;
import com.netflix.appinfo.InstanceInfo;
import com.order.Order;
import com.order.OrderItem;
import com.order.repository.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.discovery.EurekaClient;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@RestController
public class GatewayController {
    @Autowired
    private EurekaClient eurekaClient;


    @GetMapping("api/report/items/")
    public HashMap <String, Float> getItemUsageStatistics() {
        List <Item> items = getItems();
        List <Order> orders = getOrders();

        for (int i = 0; i < items.size(); i++) {
            Item item = items.get(i);
            for (int j = 0; j < orders.size(); j++) {
                Order order = orders.get(j);
                OrderItem orderItem = new OrderItem();
                orderItem.setItemId(item.getId());
                orderItem.setOrderId(order.getId());

            }
        }
    }

    private List <Item> getItems() {
        InstanceInfo clinet = eurekaClient.getNextServerFromEureka("item-client", false);
        String itemUrl = clinet.getHomePageUrl() + "/api/warehouse/items/";
        List<Item> list = new RestTemplate().getForObject(itemUrl, List.class);
        return list;
    }

    private List<Order> getOrders() {
        InstanceInfo clinet = eurekaClient.getNextServerFromEureka("order-client", false);
        String itemUrl = clinet.getHomePageUrl() + "/api/orders";
        List<Order> list = new RestTemplate().getForObject(itemUrl, List.class);
        return list;
    }

}