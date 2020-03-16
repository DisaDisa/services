package com.zuul;

import com.item.Item;
import com.netflix.appinfo.InstanceInfo;
import com.order.Order;
import com.order.OrderItem;
import com.order.dto.OrderItemDto;
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
        HashMap<String, Float> stat = new HashMap<String, Float>();

        for (int i = 0; i < items.size(); i++) {
            Item item = items.get(i);
            Integer cnt = 0;
            for (int j = 0; j < orders.size(); j++) {
                Order order = orders.get(j);
                Integer order_id = order.getId();
                OrderItemDto orderItem = getOrderItem(order_id);
                for (Item cur_item : orderItem.getItems()) {
                    if (cur_item.getId() == item.getId()) {
                        cnt++;
                    }
                }
            }
            stat.put(item.getName(), (float) (cnt / orders.size()));
        }
        return stat;
    }

    private List <Item> getItems() {
        InstanceInfo clinet = eurekaClient.getNextServerFromEureka("item-client", false);
        String itemUrl = clinet.getHomePageUrl() + "/api/warehouse/items/";
        List<Item> list = new RestTemplate().getForObject(itemUrl, List.class);
        return list;
    }

    private List<Order> getOrders() {
        InstanceInfo clinet = eurekaClient.getNextServerFromEureka("order-client", false);
        String orderUrl = clinet.getHomePageUrl() + "/api/orders";
        List<Order> list = new RestTemplate().getForObject(orderUrl, List.class);
        return list;
    }

    private OrderItemDto getOrderItem(Integer id) {
        InstanceInfo clinet = eurekaClient.getNextServerFromEureka("order-client", false);
        String orderUrl = clinet.getHomePageUrl() + "/api/orders/" + id.toString();
        OrderItemDto orderItem = new RestTemplate().getForObject(orderUrl, OrderItemDto.class);
        return orderItem;
    }
}