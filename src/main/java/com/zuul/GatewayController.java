package com.zuul;

import com.item.Item;
import com.netflix.appinfo.InstanceInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.eureka.EurekaDiscoveryClient;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.discovery.EurekaClient;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;


@RestController
public class GatewayController {
    @Autowired
    private EurekaClient eurekaClient;


    private List <Item> getItems() {
        InstanceInfo clinet = eurekaClient.getNextServerFromEureka("item-client", false);
        String itemUrl = clinet.getHomePageUrl() + "/api/warehouse/items/";
        ArrayList<Item> list = new RestTemplate().getForObject(itemUrl, ArrayList.class);
        return list;
    }

}